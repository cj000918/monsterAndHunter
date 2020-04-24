package com.chenjian.entity;


import com.chenjian.util.*;
import lombok.Data;

import java.io.Serializable;


@Data
public class MonsterNew {

    private  Long curLife;
    private  Long maxLife;
    private  String title;
    private  Integer isLive;         //0为存活; 1死亡
    private  Long maxAttack;         //最大攻击力
    private  Long minAttack;			//最小攻击力
    private  Long defend;         //防御力
    private  Long agile;          //敏捷
    private Long hideRate;       //躲避率
    private  String name;		//名称
    private String grade;		//级别
    private String profession;  //职业
    private  String monsterId;

    public MonsterNew(){}

    public MonsterNew(HunterNew hunter){

    	this.name = MonsterUtil.getMonsterName(hunter.getLevel());
    	this.grade = MonsterUtil.getMonsterGrade(hunter.getLevel());
    	this.profession = MonsterUtil.getMonsterProfession(hunter.getLevel());
    	this.title = grade + name + profession;

    	this.hideRate = MonsterUtil.getHideRate(hunter.getHideRate());
    	this.maxAttack = MonsterUtil.getMaxAttack(grade);
    	this.minAttack = MonsterUtil.getMinAttack(grade);
    	this.agile = MonsterUtil.getAgile(grade);
    	this.defend = MonsterUtil.getDefend(agile);
    	this.maxLife = MonsterUtil.getMonsterMaxLife(defend);
    	
    	this.curLife = maxLife;
    	this.isLive = 0;//0 表示存活
        this.monsterId = SnowflakeIdWorker.getSnowFlakeId()+"";
    }

}
