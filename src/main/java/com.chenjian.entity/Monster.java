package com.chenjian.entity;


import com.chenjian.util.GameUtil;
import com.chenjian.util.MonsterUtil;

public class Monster{
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
   
    
//    public Monster(int mt){
//        switch(mt){
//        case 1: type = "超级丧尸";maxLife = 80; curLife = 80;  maxAttack = 25; minAttack = 15; defend = 15;    agile = 30;hideRate = 30;   break;
//        case 2: type = "变异丧尸";maxLife = 80; curLife = 60;  maxAttack = 28; minAttack = 18; defend = 10;    agile = 40;hideRate = 20;   break;
//        case 3: type = "普通丧尸";maxLife = 80; curLife = 40;  maxAttack = 10; minAttack = 3; defend = 5;     agile = 30;hideRate = 10;   break;
//        case 4: type = "吸血鬼"; maxLife = 80; curLife = 60; maxAttack = 20; minAttack = 11; defend = 8; agile = 30;hideRate = 50; break;
//        default: type = "隐藏BOSS"; maxLife = 100; curLife = 100; maxAttack = 50; minAttack = 30; defend = 20; agile = 60; hideRate = 60; break;
//        }
//    }
    
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
    }
}
