/**
 * projectName: mh
 * fileName: HunterNew.java
 * packageName: com.chenjian.entity
 * date: 2019-08-30 18:47
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.entity;

import lombok.Data;

/**
 * @author: ChenJian
 * @description: 新猎人
 * @data: 2019-08-30 18:47
 **/
@Data
public class HunterNew{

    private String name;
    private Long maxLife;
    private Long curLife;
    private Integer isLive;         //0为存活; 1死亡
    private Long maxAttack;         //最大攻击力
    private Long minAttack;			//最小攻击力
    private Long defend;         //防御力
    private Long level;
    private Long exp;
    private Long needExp;
    private Long agile;
    private Long hideRate;

    private String hunterId;

    public HunterNew(){

//        this.name = "小高炮";
        this.maxLife = 100L;
        this.curLife = 100L;
        this.isLive = 0;//0 表示存活
        this.maxAttack = 10L;
        this.minAttack = 5L;
        this.defend = 3L;
        this.level = 1L;
        this.exp = 0L;
        this.needExp = 100L;
        this.agile = 3L;
        this.hideRate = 3L;
    }

}