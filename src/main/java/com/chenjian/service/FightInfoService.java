/**
 * projectName: mh
 * fileName: FightInfoService.java
 * packageName: com.chenjian.service
 * date: 2019-09-13 11:18
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.service;

import com.chenjian.entity.base.FightInfo;
import com.chenjian.entity.base.HunterNew;
import com.chenjian.entity.base.MonsterNew;

import java.util.List;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-13 11:18
 **/
public interface FightInfoService {


        String fightings(HunterNew hunterNew, MonsterNew monsterNews);

        Long addFightInfo(FightInfo fightInfo);

        List<FightInfo> getFightInfo(Long hunterId);

        List<FightInfo> getFightInfoList(Long hunterId);

}