/**
 * projectName: mh
 * fileName: NewGameController.java
 * packageName: com.chenjian.controller
 * date: 2019-08-30 18:52
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.controller;

import com.chenjian.entity.HunterNew;
import com.chenjian.service.HunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-08-30 18:52
 **/
@RestController
@RequestMapping("hunter")
public class NewGameController {

    @Autowired
    private HunterService hunterService;


    /**
     *
     * @return
     */
    @GetMapping("add_hunter")
    public String addHuter(){

        HunterNew hunterNew = new HunterNew();

        hunterNew.setAgile(10);
        hunterNew.setCurLife(1);
        hunterNew.setDefend(1);
        hunterNew.setExp(100);
        hunterNew.setHideRate(1);

        hunterNew.setLevel(1);
        hunterNew.setLive(true);
        hunterNew.setMaxAttack(1);
        hunterNew.setMaxLife(1);
        hunterNew.setMinAttack(1);

        hunterNew.setName("小高炮");
        hunterNew.setNeedExp(1);

       long result =  hunterService.addHunter(hunterNew);

       if(result > 0){
           return "添加成功";
       }else{
           return "添加失败";
       }
    }


}