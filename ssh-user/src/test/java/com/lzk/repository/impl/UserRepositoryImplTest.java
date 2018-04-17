package com.lzk.repository.impl;

import com.lzk.entity.User;
import com.lzk.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-hibernate.xml"})
public class UserRepositoryImplTest extends TestCase {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testLoad() {
        User user = userRepository.load(11);
        System.out.println(user);
    }

    /**
     * load 和 get区别
     * 若查询数据不存在
     * load会报 ObjectNotFoundException异常
     * get返回 null
     */
    @Test
    public void testGet() {
        User user = userRepository.get(11);
        System.out.println(user);
    }

    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println); //todo 无法查询所有
    }

    @Test
    public void testPersist() {
        User user = new User();
        userRepository.persist(user);//todo 这个是做什么的
    }

    @Test
    public void testSave() {
        User user = new User("admin","admin","浙江","18000000000");
        userRepository.save(user);
    }

    @Test
    public void testSaveOrUpdate() {
        User user = new User("admin1","admin","zhejiang","18000000000");
        user.setId(1);
        userRepository.saveOrUpdate(user);//todo 修改不了
    }

    @Test
    public void testDelete() {
        userRepository.delete(1);//todo 删除不了
    }

    @Test
    public void testFlush() {
        userRepository.flush();
    }
}