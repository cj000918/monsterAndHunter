/**
 * projectName: mh
 * fileName: MonsterServiceImpl.java
 * packageName: com.chenjian.service.Impl
 * date: 2019-09-13 11:13
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.service.Impl;

import com.chenjian.dao.MonsterDao;
import com.chenjian.entity.Hunter;
import com.chenjian.entity.HunterNew;
import com.chenjian.entity.MonsterNew;
import com.chenjian.service.MonsterService;
import com.chenjian.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-13 11:13
 **/
@Service
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    private MonsterDao monsterDao;

    @Override
    public MonsterNew addMonsterByHunter(HunterNew hunterNew) {

        MonsterNew monsterNew = new MonsterNew(hunterNew);

       long result = monsterDao.addMonsterInfo(monsterNew);

       if(result < 0){
           return null;
       }

        return monsterNew;
    }


    /**
     * 详细信息展示
     */
    @Override
    public void showMonsterInfo(MonsterNew monster){

        System.out.println(" 名称: " + "【"+monster.getTitle()+"】");
        System.out.println(" 状态: " + monster.getIsLive());
        System.out.println(" 血量: " + monster.getCurLife());
        System.out.println(" 攻击力: " + monster.getMinAttack()+"-"+monster.getMaxAttack());
        System.out.println(" 防御力: " + monster.getDefend());
        System.out.println(" 敏捷: " + monster.getAgile());
        System.out.println(" 闪避率: " + monster.getHideRate()+"\r\n");
    }


    /**
     * 被杀
     * @param monster
     */
    @Override
    public Long died(MonsterNew monster){

        monster.setIsLive(0);
        System.out.println("【"+monster.getTitle()+"】"+" 被砍的四分五裂了 ."+"\r\n");
        return monster.getMaxLife();
    }



}