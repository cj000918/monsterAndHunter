/**
 * projectName: mh
 * fileName: NewGameController.java
 * packageName: com.chenjian.controller
 * date: 2019-08-30 18:52
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.controller;

import com.chenjian.entity.Hunter;
import com.chenjian.entity.HunterNew;
import com.chenjian.entity.MonsterNew;
import com.chenjian.service.FightService;
import com.chenjian.service.HunterService;
import com.chenjian.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-08-30 18:52
 **/
@RestController
@RequestMapping("/hunter")
public class NewGameController {

    @Autowired
    private HunterService hunterService;

    @Autowired
    private MonsterService monsterService;

    @Autowired
    private FightService fightService;


    /**
     * 创建猎人
     * @return
     */
    @PostMapping("/add_hunter")
    public String addHuter(HunterNew hunterNew){

       long result =  hunterService.addHunter(hunterNew);

       if(result > 0){
           return "添加成功";
       }else{
           return "添加失败";
       }
    }


    @GetMapping("/fight")
    public Map<String, Object> doFight(Long hunterId){

        Map<String, Object> map = new HashMap<>();

        HunterNew hunterNew = hunterService.getHunterNewById(hunterId);

        MonsterNew monsterNew = monsterService.addMonsterByHunter(hunterNew);

       String result = fightService.fightings(hunterNew, monsterNew);

        return map;
    }



}