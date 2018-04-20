package com.lzk.controller;

import com.lzk.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

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
        User user = new User(1,"张三",new java.sql.Date(new Date().getTime()));
        model.addAttribute("user",user);
        model.addAttribute("html","<font color = 'red'>我只是个菜单</font>");

        List<String> strList = new ArrayList<String>();
        strList.add("第一个值");
        strList.add("第二个值");
        strList.add("第三个值");
        model.addAttribute("strList",strList);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("name","姓名");
        m.put("age",18);
        m.put("sex","男");
        model.addAttribute("m",m);

        model.addAttribute("sort_int",new SortMethod());
        return "cp";
    }
}
