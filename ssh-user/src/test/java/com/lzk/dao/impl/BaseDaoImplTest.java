package com.lzk.dao.impl;

import com.lzk.dao.BaseDao;
import com.lzk.entity.Person;
import com.lzk.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BaseDaoImplTest {
    @Resource
    private BaseDao baseDao;

    @Test
    public void save() {
        Person person = new Person(UUIDUtils.getUUID(),"1111","杭州","11111111111111","11");
        baseDao.save(person);
    }

    @Test
    public void delete() {
        Person person = baseDao.get(Person.class,"061bc60faeb2479b98102cf2fd9ce2c2");
        baseDao.delete(person);
    }

    @Test
    public void update() {
        Person person = baseDao.get(Person.class,"0669d16de8fc4d9b9fd31b9cba1f52a6");
        System.out.println(person);
        baseDao.update(person);
    }

    @Test
    public void saveOrUpdate() {
        Person person = new Person("0fc3ae1f2d1849fd919aa24f7bc7cc5e","1111","杭州","11111111111111","11");
        System.out.println(person);
        baseDao.saveOrUpdate(person);
    }

    @Test
    public void list() {
        List<Person> list = baseDao.list(Person.class);
        list.forEach(System.out::println);
    }

    @Test
    public void find() {
        String hql = "from Person";
        List<Person> list = baseDao.find(hql,3,5);
        list.forEach(System.out::println);
    }

    @Test
    public void findBySql() {
        String sql = "select id,address,created,phone,remark,username from person where address = :address";
        Map<String,Object> map = new HashMap<>();
        map.put("address","杭州");
        List<Person> list = baseDao.findBySql(sql,map,2,5,Person.class);
        list.forEach(System.out::println);
    }

    @Test
    public void countBySql() {
        String sql = "select count(*) from person where address = '杭州'";
        /*
        参数查询  有问题
        Map<String,Object> map = new HashMap<>();
        map.put("address","杭州");
        Long count = baseDao.countBySql(sql,map);*/
        Long count = baseDao.countBySql(sql);
        System.out.println(count);
    }

}