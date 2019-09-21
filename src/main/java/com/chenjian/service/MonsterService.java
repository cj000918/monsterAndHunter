/**
 * projectName: mh
 * fileName: MonsterService.java
 * packageName: com.chenjian.service
 * date: 2019-09-13 11:13
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.service;

import com.chenjian.entity.HunterNew;
import com.chenjian.entity.MonsterNew;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-13 11:13
 **/
public interface MonsterService {

    MonsterNew addMonsterByHunter(HunterNew hunterNew);

    void showMonsterInfo(MonsterNew monster);

    Long died(MonsterNew monster);

}