/**
 * projectName: mh
 * fileName: NewGameController.java
 * packageName: com.chenjian.controller
 * date: 2019-08-30 18:52
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.controller;

import com.chenjian.entity.base.FightInfo;
import com.chenjian.entity.base.HunterNew;
import com.chenjian.entity.base.MonsterNew;
import com.chenjian.entity.base.WeaponNew;
import com.chenjian.entity.response.RestResponse;
import com.chenjian.enums.BizExceptionCode;
import com.chenjian.exception.BizException;
import com.chenjian.service.FightInfoService;
import com.chenjian.service.HunterService;
import com.chenjian.service.MonsterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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
    public Long addHuter(@RequestBody HunterNew hunterNew){

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

        Long result =  hunterService.addHunter(hunterNew);

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
    @GetMapping("/fight/{hunterName}")
    @ResponseBody
    public RestResponse<Map<String, Object>> doFight(@PathVariable("hunterName") String hunterName){

        if(ObjectUtils.isEmpty(hunterName)){
            throw new BizException(BizExceptionCode.PARAMETER_MISSING.getCode(), BizExceptionCode.PARAMETER_MISSING.getMsg());
        }

        Map<String, Object> map = new HashMap<>();
        HunterNew oldHunterNew = hunterService.getHunterNewByName(hunterName);
        HunterNew newHunterNew = new HunterNew();

        //判断当前名称对应的hunter是否存在，若存在则直接战斗
        //若不存在，则需要先添加hunter再进行战斗
        if(oldHunterNew != null && oldHunterNew.getCurLife() > 0){
            BeanUtils.copyProperties(oldHunterNew, newHunterNew);
        }else{
            HunterNew hunter = new HunterNew(hunterName);
            WeaponNew weaponNew = new WeaponNew();
            weaponNew.showWeaponInfo();
            Long addResult = hunterService.addHunter(hunter);
            if(null != addResult && !"".equals(addResult)){
                newHunterNew = hunterService.getHunterNewById(addResult);
            }
        }

        MonsterNew monsterNew = monsterService.addMonsterByHunter(newHunterNew);
        String result = fightService.fightings(newHunterNew, monsterNew);

        map.put("msg", result);
        map.put("hunterId", newHunterNew.getHunterId().toString());
        map.put("code", 100);

        return new RestResponse<>(map);
    }

    /**
     * 查询hunter信息
     * @param hunterName
     * @return
     */
    @GetMapping("/hunterId/{hunterName}")
    public Long getHunterId(@PathVariable("hunterName") String hunterName){

        Long hunterId = null;

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
    @GetMapping("/getFighting/{hunterId}")
    public Map<String, Object> getFightingInfo(@PathVariable("hunterId") String hunterId){

        Map<String, Object> map = new HashMap<>();

        map.put("msg","");
        map.put("code", 300);

        if(StringUtils.isBlank(hunterId)){
            return map;
        }
        List<FightInfo> fightInfoList =  fightService.getFightInfo(Long.parseLong(hunterId));


        map.put("fights", fightInfoList);
        map.put("msg","");
        map.put("code", 100);

        return map;

    }



}