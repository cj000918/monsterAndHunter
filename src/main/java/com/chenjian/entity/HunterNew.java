/**
 * projectName: mh
 * fileName: HunterNew.java
 * packageName: com.chenjian.entity
 * date: 2019-08-30 18:47
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.entity;

import java.io.Serializable;

/**
 * @author: ChenJian
 * @description: 新猎人
 * @data: 2019-08-30 18:47
 **/
public class HunterNew implements Serializable {

    String name;
    long maxLife;
    long curLife;
    boolean isLive;
    long maxAttack;         //最大攻击力
    long minAttack;			//最小攻击力
    long defend;         //防御力
    long level;
    long exp;
    long needExp;
    long agile;
    long hideRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(long maxLife) {
        this.maxLife = maxLife;
    }

    public long getCurLife() {
        return curLife;
    }

    public void setCurLife(long curLife) {
        this.curLife = curLife;
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

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getNeedExp() {
        return needExp;
    }

    public void setNeedExp(long needExp) {
        this.needExp = needExp;
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
}