package com.chenjian.controller;



import com.chenjian.entity.GameStart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/game")
public class GameController {



    @RequestMapping( value = "/order_statistics", method = RequestMethod.POST)
    @ResponseBody
    public void start(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        new GameStart().start();
    }
}
