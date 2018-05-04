package com.lzk.dao.impl;

import com.lzk.dao.UserDao;
import com.lzk.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring.xml"})
public class UserDaoImplTest {

    @Resource
    private UserDao userDao;

    @Test
    public void getUserByUserName() {
        User user = userDao.getUserByUserName("Mark");
        System.out.println(user);
    }

    @Test
    public void getRolesByUserName() {
        List<String> roles = userDao.getRolesByUserName("Mark");
        roles.forEach(System.out::println);
    }
}