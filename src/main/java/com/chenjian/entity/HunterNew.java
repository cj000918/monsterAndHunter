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
    private Integer isLive;
    private Long maxAttack;         //最大攻击力
    private Long minAttack;			//最小攻击力
    private Long defend;         //防御力
    private Long level;
    private Long exp;
    private Long needExp;
    private Long agile;
    private Long hideRate;

    private Long hunterId;

    public HunterNew(){

        this.name = "小高炮";
        this.maxLife = 1L;
        this.curLife = 1L;
        this.isLive = 0;
        this.maxAttack = 1L;
        this.minAttack = 1L;
        this.defend = 1L;
        this.level = 1L;
        this.exp = 1L;
        this.needExp = 1L;
        this.agile = 1L;
        this.hideRate = 1L;
    }

}