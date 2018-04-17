package com.lzk.controller;

import com.lzk.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * 取值
 */
@Controller
public class CpController {
    @RequestMapping(value = "/cp",method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("intVar",100);
        model.addAttribute("longVar",1000000L);
        model.addAttribute("strVar","我是字符串");
        model.addAttribute("doubleVar",3.45d);
        model.addAttribute("booleanVar",true);
        model.addAttribute("dateVar",new java.sql.Date(new Date().getTime()));
        model.addAttribute("nullVar",null);

        User user = new User(1,"haha",new Date());
        model.addAttribute("user",user);
        return "cp";
    }
}
