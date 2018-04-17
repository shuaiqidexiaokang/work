package com.lzk.controller;

import com.lzk.dto.Result;
import com.lzk.entity.User;
import com.lzk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        System.out.println("请求来了");
        return "index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(User user) {
        System.out.println(user);//todo ???为什么接受不了参数
        int count = userService.save(user);
        System.out.println(count);
        return null;
    }

    @RequestMapping(value = "/delete",
            method = RequestMethod.POST)
    @ResponseBody
    public void delete(@RequestParam("id") Integer id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/saveOrUpdate",
            method = RequestMethod.POST)
    @ResponseBody
    public void saveOrUpdate(User user) {
        userService.saveOrUpdate(user);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<User> get(@RequestParam("id") Integer id) {
        System.out.println(id);
        User user = userService.get(id);
        System.out.println(user);//User{id=1, username='null', password='null', address='null', phone='null'}
        return null;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll() {
        List<User> users = userService.findAll();
        users.forEach(System.out::println);
        return null;

    }
}
