/**
 * projectName: mh
 * fileName: NewGameController.java
 * packageName: com.chenjian.controller
 * date: 2019-08-30 18:52
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.controller;

import com.chenjian.entity.FightInfo;
import com.chenjian.entity.HunterNew;
import com.chenjian.entity.MonsterNew;
import com.chenjian.service.FightInfoService;
import com.chenjian.service.HunterService;
import com.chenjian.service.MonsterService;
import com.chenjian.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
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
     * 静态页面
     * @param
     * @return
     */
    @RequestMapping(value = "/star" , method = RequestMethod.GET)
    public ModelAndView getStartView() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/new_fighting");
        return mv;

    }


    /**
     * 创建猎人 第一版（暂停使用）
     * @return
     */
    @PostMapping("/add_hunter")
    @ResponseBody
    public String addHuter(@RequestBody HunterNew hunterNew){

        return hunterService.addHunter(hunterNew);
     }

    /**
     * 创建猎人 第二版（暂停使用）
     * @return
     */
    @GetMapping("/add_hunter_2")
    @ResponseBody
    public Map<String, Object> addHuter2(String hunterName){

        HunterNew hunterNew = new HunterNew();
        hunterNew.setName(hunterName);

        String result =  hunterService.addHunter(hunterNew);

        Map<String, Object> map = new HashMap<>();

        map.put("hunterId", result);
        map.put("success", true);

        return map;

    }


    /**
     * 战斗
     * @param hunterName
     * @return
     */
    @GetMapping("/fight")
    public Map<String, Object> doFight(String hunterName){

        Map<String, Object> map = new HashMap<>();

        HunterNew hunterNew = hunterService.getHunterNewByName(hunterName);

        //判断当前名称对应的hunter是否存在，若存在则直接战斗
        //若不存在，则需要先添加hunter再进行战斗
        if(hunterNew != null && hunterNew.getCurLife() > 0){
            hunterNew.setIsLive(0);
        }else{
            HunterNew hunter = new HunterNew();
            hunter.setName(hunterName);
            String addResult = hunterService.addHunter(hunter);
            if(null != addResult && !"".equals(addResult)){
                hunterNew = hunterService.getHunterNewById(addResult);
            }
        }
        MonsterNew monsterNew = monsterService.addMonsterByHunter(hunterNew);
        String result = fightService.fightings(hunterNew, monsterNew);

        map.put("msg", result);
        map.put("hunterId", hunterNew.getHunterId());
        map.put("code", 100);

        return map;
    }

    /**
     * 查询hunter信息
     * @param hunterName
     * @return
     */
    @GetMapping("/hunterId")
    public String getHunterId(String hunterName){

        String hunterId = "";

        HunterNew hunterNew = hunterService.getHunterNewByName(hunterName);

        if(hunterNew != null && hunterNew.getCurLife() > 0){
            hunterId = hunterNew.getHunterId();
        }

        return hunterId;

    }


    /**
     * 查询战斗信息
     * @param hunterId
     * @return
     */
    @GetMapping("/get_fighting")
    public Map<String, Object> getFightingInfo(String hunterId){

        Map<String, Object> map = new HashMap<>();

        map.put("msg","");
        map.put("code", 300);

        if(null == hunterId || "".equals(hunterId)){
            return map;
        }

        List<FightInfo> fightInfoList =  fightService.getFightInfo(hunterId);


        map.put("fights", fightInfoList);
        map.put("msg","");
        map.put("code", 100);

        return map;

    }



}