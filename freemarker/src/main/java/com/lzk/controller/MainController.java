package com.lzk.controller;

import com.lzk.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("username","FreeMarker");
        model.addAttribute("nullVar","A");
        model.addAttribute("name","aaa");
        List<User> users = prepareUserList();
        model.addAttribute("users",users);
        return "index";
    }

    public List<User> prepareUserList(){
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 5 ; i++) {
            User user = new User(i,"用户" + (i+1),new Date());
            users.add(user);
        }
        return users;
    }
}
