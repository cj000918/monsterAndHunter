package com.chenjian.entity;


import com.chenjian.util.GameUtil;
import com.chenjian.util.MonsterUtil;
import com.chenjian.util.RedisUtil;


public class Monster{

    private static RedisUtil redisUtil;

    public static void setRedisUtil(RedisUtil redisUtil1){
        redisUtil = redisUtil1;
    }

    long curLife;
    long maxLife;
    String type;
    boolean isLive = true;
    long maxAttack;         //最大攻击力
    long minAttack;			//最小攻击力
    long defend;         //防御力
    long agile;          //敏捷
    long hideRate;       //躲避率
    String name;		//名称
    String grade;		//级别
    String profession;  //职业



    
    public Monster(Hunter hunter){
    	
    	this.name = MonsterUtil.getMonsterName(hunter.level);
    	this.grade = MonsterUtil.getMonsterGrade(hunter.level);
    	this.profession = MonsterUtil.getMonsterProfession(hunter.level);
    	this.type = grade + name + profession;
    	
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
            System.out.println("【"+type+"】"+" 躲过了 "+"【"+hunter.name+"】"+"的攻击"+"\r\n");
            showLiveStatus();
            kill(hunter);
            return;
        }
        
        System.out.println("【"+type+"】"+" 受到攻击 "+"\r\n");
        
        long lostLife = GameUtil.calLostLife(hunter.maxAttack, hunter.minAttack, this.defend);
        
        System.out.println("【"+type+"】"+" 血量: -"+lostLife+"\r\n");
     
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
        
        System.out.println("【"+type+"】"+" 被砍的四分五裂了 "+isLive+"\r\n");
        System.out.println("【"+hunter.name+"】" + " 增加经验:  "+maxLife+"\r\n");
        
        hunter.expAdd(this);    //this
    }
    
    /**
     * 击杀
     * @param hunter
     */
    public void kill(Hunter hunter){
        if(isLive){
	        System.out.println("【"+type+"】"+" 冲上去咬了 "+"【"+hunter.name+"】"+"一大口"+"\r\n");
	        hunter.injured(this);
        }else{
            System.out.println("【"+type+"】"+" 已经被砍的四分五裂了 "+"\r\n");
        }
    }
    
    /**
     * 展示存活状态
     */
    public void showLiveStatus(){
        System.out.println("【"+type+"】"+" 生命值 "+curLife+"  "+" 生命状态 "+isLive+"\r\n");
    }

    /**
     * 详细信息展示
     */
    public void showMonsterInfo(){


        System.out.println(" 名称: " + "【"+type+"】");
		System.out.println(" 状态: " + isLive);
		System.out.println(" 血量: " + curLife);
		System.out.println(" 攻击力: " + minAttack+"-"+maxAttack);
		System.out.println(" 防御力: " + defend);
		System.out.println(" 敏捷: " + agile);
		System.out.println(" 闪避率: " + hideRate+"\r\n");


		redisUtil.hset("Monster_info","monsterName",type);
        redisUtil.hset("Monster_info","monsterIsLive",isLive);
        redisUtil.hset("Monster_info","monsterCurLife",curLife);
        redisUtil.hset("Monster_info","monsterMinAttack",minAttack);
        redisUtil.hset("Monster_info","monsterMaxAttack",maxAttack);
        redisUtil.hset("Monster_info","monsterDefend",defend);
        redisUtil.hset("Monster_info","monsterAgile",agile);
        redisUtil.hset("Monster_info","monsterHideRate",hideRate);

    }
}
