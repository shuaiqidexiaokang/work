package com.lzk.controller;

import com.lzk.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public User getUser(User user){
        return user;
    }

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    @ResponseBody
    public User postUser(User user){
        System.out.println(user);
        return user;
    }
}
