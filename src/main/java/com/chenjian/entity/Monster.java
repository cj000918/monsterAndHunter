package com.chenjian.entity;


import com.chenjian.util.DateUtil;
import com.chenjian.util.GameUtil;
import com.chenjian.util.MonsterUtil;
import com.chenjian.util.RedisUtil;

import java.io.Serializable;
import java.util.Date;


public class Monster implements Serializable {

    private static final long serialVersionUID = 1001L;

    private static RedisUtil redisUtil;

    public static void setRedisUtil(RedisUtil redisUtil1){
        redisUtil = redisUtil1;
    }

    long curLife;
    long maxLife;
    String title;
    boolean isLive = true;
    long maxAttack;         //最大攻击力
    long minAttack;			//最小攻击力
    long defend;         //防御力
    long agile;          //敏捷
    long hideRate;       //躲避率
    String name;		//名称
    String grade;		//级别
    String profession;  //职业
    long monsterId;

    public long getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(long monsterId) {
        this.monsterId = monsterId;
    }

    public long getCurLife() {
        return curLife;
    }

    public void setCurLife(long curLife) {
        this.curLife = curLife;
    }

    public long getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(long maxLife) {
        this.maxLife = maxLife;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public long getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(long maxAttack) {
        this.maxAttack = maxAttack;
    }

    public long getMinAttack() {
        return minAttack;
    }

    public void setMinAttack(long minAttack) {
        this.minAttack = minAttack;
    }

    public long getDefend() {
        return defend;
    }

    public void setDefend(long defend) {
        this.defend = defend;
    }

    public long getAgile() {
        return agile;
    }

    public void setAgile(long agile) {
        this.agile = agile;
    }

    public long getHideRate() {
        return hideRate;
    }

    public void setHideRate(long hideRate) {
        this.hideRate = hideRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Monster(Hunter hunter){

    	this.name = MonsterUtil.getMonsterName(hunter.level);
    	this.grade = MonsterUtil.getMonsterGrade(hunter.level);
    	this.profession = MonsterUtil.getMonsterProfession(hunter.level);
    	this.title = grade + name + profession;

    	this.hideRate = MonsterUtil.getHideRate(hunter.hideRate);
    	this.maxAttack = MonsterUtil.getMaxAttack(grade);
    	this.minAttack = MonsterUtil.getMinAttack(grade);
    	this.agile = MonsterUtil.getAgile(grade);
    	this.defend = MonsterUtil.getDefend(agile);
    	this.maxLife = MonsterUtil.getMonsterMaxLife(defend);
    	
    	this.curLife = maxLife;
    	
    }

    /**
     * 扣血
     * @param hunter
     */
    public void injured(Hunter hunter){
        //增加躲避的判断
        if(GameUtil.hidden(this.hideRate)){

            System.out.println("【"+title+"】"+" 躲过了 "+"【"+hunter.name+"】"+"的攻击"+"\r\n");

            redisUtil.lSet("fight_info_"+hunter.name, DateUtil.getNowTime()+"【"+title+"】"+" 躲过了 "+"【"+hunter.name+"】"+"的攻击");

            showLiveStatus();
            kill(hunter);
            return;
        }
        
        System.out.println("【"+title+"】"+" 受到攻击 "+"\r\n");

        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime()+"【"+title+"】"+" 受到攻击 ");
        
        long lostLife = GameUtil.calLostLife(hunter.maxAttack, hunter.minAttack, this.defend);
        
        System.out.println("【"+title+"】"+" 血量: -"+lostLife+"\r\n");

        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime()+"【"+title+"】"+" 血量: -"+lostLife);
     
        curLife-=lostLife;
        
        if(curLife < 1){
            curLife = 0;
            died(hunter);
            return;
        }
        showLiveStatus();
        kill(hunter);
    }
    
    /**
     * 被杀
     * @param hunter
     */
    public void died(Hunter hunter){
        this.isLive = false;
        
        System.out.println("【"+title+"】"+" 被砍的四分五裂了 "+isLive+"\r\n");
        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime()+"【"+title+"】"+" 被砍的四分五裂了 "+isLive);

        System.out.println("【"+hunter.name+"】" + " 增加经验:  "+maxLife+"\r\n");

        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime()+"【"+hunter.name+"】" + " 增加经验:  "+maxLife);
        
        hunter.expAdd(this);
    }
    
    /**
     * 击杀
     * @param hunter
     */
    public void kill(Hunter hunter){
        if(isLive){

            System.out.println("【"+title+"】"+" 冲上去咬了 "+"【"+hunter.name+"】"+"一大口"+"\r\n");

            redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime()+"【"+title+"】"+" 冲上去咬了 "+"【"+hunter.name+"】"+"一大口");

	        hunter.injured(this);
        }else{

            System.out.println("【"+title+"】"+" 已经被砍的四分五裂了 "+"\r\n");

            redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime()+"【"+title+"】"+" 已经被砍的四分五裂了 ");
        }
    }
    
    /**
     * 展示存活状态
     */
    public void showLiveStatus(){
        System.out.println("【"+title+"】"+" 生命值 "+curLife+"  "+" 生命状态 "+isLive+"\r\n");
    }

    /**
     * 详细信息展示
     */
    public void showMonsterInfo(Hunter hunter){

        System.out.println(" 名称: " + "【"+title+"】");
		System.out.println(" 状态: " + isLive);
		System.out.println(" 血量: " + curLife);
		System.out.println(" 攻击力: " + minAttack+"-"+maxAttack);
		System.out.println(" 防御力: " + defend);
		System.out.println(" 敏捷: " + agile);
		System.out.println(" 闪避率: " + hideRate+"\r\n");

        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime()+"【"+title+"】"+" 状态: " + isLive+" 血量: " + curLife+" 攻击力: " + minAttack+"-"+maxAttack+" 防御力: " + defend+" 敏捷: " + agile+" 闪避率: " + hideRate);
//        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime());
//        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime());
//        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime());
//        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime());
//        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime());
//        redisUtil.lSet("fight_info_"+hunter.name,DateUtil.getNowTime());
    }
}
