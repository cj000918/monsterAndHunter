package com.chenjian.controller;


import com.chenjian.entity.GameStart;
import com.chenjian.entity.Message;
import com.chenjian.util.RedisUtil;
import com.chenjian.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    GameStart gameStart;


    /**
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/star" , method = RequestMethod.GET)
    public ModelAndView getStartView() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/greeting");
        return mv;

    }

    /**
     *
     */
    @RequestMapping( value = "/game", method = RequestMethod.GET)
    @ResponseBody
    public Message getStartInfo(@RequestParam String name) {

        Message message = new Message();

        if (name == null || name.trim().length() < 1) {

            message.setMsg("名字不能为空,请重新输入...");
            message.setSuccess(false);

            return message;
        }else{

            redisUtil.del("Monster_id_list");

             gameStart.setName(name).start();
        }
        message.setSuccess(true);
        return message;
    }


    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public Message getMessage(@RequestParam String name){

        Message message = new Message();
        if(StringUtil.isNullTrim(name)){
            return Message.fail("用户名称不能为空哦");
        }

        String redisName = "fight_info_"+name;

        long redisCount = redisUtil.lGetListSize(redisName);

        List<Object> fightInfoList =  redisUtil.lGet(redisName, 0, redisCount);

        return Message.success("查询成功", fightInfoList);
    }

}
