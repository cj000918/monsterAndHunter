/**
 * projectName: mh
 * fileName: HunterNew.java
 * packageName: com.chenjian.entity
 * date: 2019-08-30 18:47
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.entity.base;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author: ChenJian
 * @description: 新猎人
 * @data: 2019-08-30 18:47
 **/
@Data
@Table(name = "hunter_info")
public class HunterNew{

    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "max_life")
    private Long maxLife;

    @Column(name = "cur_life")
    private Long curLife;

    @Column(name = "is_live")
    private Integer isLive;         //0为存活; 1死亡

    @Column(name = "max_attack")
    private Long maxAttack;         //最大攻击力

    @Column(name = "min_attack")
    private Long minAttack;			//最小攻击力

    @Column(name = "defend")
    private Long defend;         //防御力

    @Column(name = "level")
    private Long level;

    @Column(name = "exp")
    private Long exp;

    @Column(name = "need_exp")
    private Long needExp;

    @Column(name = "agile")
    private Long agile;

    @Column(name = "hide_rate")
    private Long hideRate;

    @Column(name = "hunter_id")
    private Long hunterId;

    @Column(name = "create_time")
    private Timestamp createTime;


    public HunterNew(){
        this.isLive = 0;//0 表示存活
    }

    public HunterNew(String name){

        this.name = name;
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