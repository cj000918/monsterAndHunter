package com.chenjian.controller;



import com.chenjian.entity.GameStart;
import com.chenjian.entity.Message;
import com.chenjian.util.RedisUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
             gameStart.setName(name).start();
        }
        message.setSuccess(true);
        return message;
    }


    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public Message getMessage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        Message message = new Message();

        for (int i = 0; i < 3; i++) {

           redisUtil.lSet("hh","哈哈");
        }


        return message;
    }

}
