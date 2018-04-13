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
        int count = userService.save(user);
        if (count > 0) {
            return new Result(true, "添加成功");
        }
        return new Result(false, "添加失败");
    }

    @RequestMapping(value = "/delete",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void delete(@RequestParam("id") Integer id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/saveOrUpdate",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void saveOrUpdate(User user) {
        userService.saveOrUpdate(user);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Result<User> get(@RequestParam("id") Integer id) {
        User user = userService.get(id);
        List<User> users = userService.findAll();
        if (user!=null) {
            return new Result<>(true, user, "查询成功");
        }
        return new Result<>(false, "查询失败");
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
        /*if (users != null && !users.isEmpty()) {
            return new Result<>(true, users, "查询成功");
        }
        return new Result<>(false, "查询失败");*/

    }
}
