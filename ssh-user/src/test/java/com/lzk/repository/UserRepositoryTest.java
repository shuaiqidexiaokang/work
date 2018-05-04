package com.lzk.repository;

import com.lzk.entity.User;
import com.lzk.repository.UserRepository;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-hibernate.xml"})
public class UserRepositoryTest extends TestCase {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave(){
        User user = new User();
        user.setPhone("111");
        userRepository.save(user);

    }

}