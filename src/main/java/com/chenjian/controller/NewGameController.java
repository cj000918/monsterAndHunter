/**
 * projectName: mh
 * fileName: NewGameController.java
 * packageName: com.chenjian.controller
 * date: 2019-08-30 18:52
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.controller;

import com.chenjian.entity.HunterNew;
import com.chenjian.entity.MonsterNew;
import com.chenjian.service.FightInfoService;
import com.chenjian.service.HunterService;
import com.chenjian.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private FightInfoService fightService;


    /**
     * 创建猎人
     * @return
     */
    @PostMapping("/add_hunter")
    @ResponseBody
    public String addHuter(@RequestBody HunterNew hunterNew){

       long result =  hunterService.addHunter(hunterNew);

       return String.valueOf(result);
    }


    @GetMapping("/fight")
    public Map<String, Object> doFight(Long hunterId){

        Map<String, Object> map = new HashMap<>();

        HunterNew hunterNew = hunterService.getHunterNewById(hunterId);

        if(hunterNew != null && hunterNew.getCurLife() > 0){
            hunterNew.setIsLive(0);
        }
        MonsterNew monsterNew = monsterService.addMonsterByHunter(hunterNew);

        String result = fightService.fightings(hunterNew, monsterNew);

        map.put("msg", result);
        map.put("code", 100);

        return map;
    }



}