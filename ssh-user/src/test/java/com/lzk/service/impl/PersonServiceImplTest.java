package com.lzk.service.impl;

import com.lzk.entity.Person;
import com.lzk.service.PersonService;
import com.lzk.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PersonServiceImplTest {


    @Resource
    private PersonService personService;

    @Test
    public void save() {
        Person person1 = new Person(UUIDUtils.getUUID(),"1111","杭州","11111111111111","11");
        Person person2 = new Person(UUIDUtils.getUUID(),"2222","温州","22222222222222","22");
        Person person3 = new Person(UUIDUtils.getUUID(),"3333","宁波","33333333333333","33");
        Person person4 = new Person(UUIDUtils.getUUID(),"4444","衢州","44444444444444","44");
        Person person5 = new Person(UUIDUtils.getUUID(),"5555","上海","55555555555555","55");
        Person person6 = new Person(UUIDUtils.getUUID(),"6666","北京","66666666666666","66");
        personService.save(person1);
        personService.save(person2);
        personService.save(person3);
        personService.save(person4);
        personService.save(person5);
        personService.save(person6);
    }

    @Test
    public void delete() {


    }

    @Test
    public void update() {

    }

    @Test
    public void saveOrUpdate() {
    }

    @Test
    public void batchSave() {
    }

    @Test
    public void batchUpdate() {
    }

    @Test
    public void batchDelete() {
    }

    @Test
    public void batchSaveOrUpdate() {
    }

    @Test
    public void list() {
    }

    @Test
    public void get() {

    }

    @Test
    public void get1() {
    }

    @Test
    public void get2() {
    }

    @Test
    public void find() {
        String sql = "from Person where remark = 11";
        List<Person> person = personService.find(sql);
        person.forEach(System.out::println);
    }

    @Test
    public void find1() {
    }

    @Test
    public void find2() {
    }

    @Test
    public void find3() {
    }

    @Test
    public void count() {
    }

    @Test
    public void count1() {
    }

    @Test
    public void executeHql() {
    }

    @Test
    public void getBySql() {
    }

    @Test
    public void getBySql1() {
    }

    @Test
    public void findBySql() {
    }

    @Test
    public void findBySql1() {
    }

    @Test
    public void findBySql2() {
    }

    @Test
    public void findBySql3() {
    }

    @Test
    public void countBySql() {
    }

    @Test
    public void countBySql1() {
    }

    @Test
    public void find_procedure() {
    }

    @Test
    public void find_procedure_multi() {
    }

    @Test
    public void find_procedure_list() {
    }

    @Test
    public void upd_procedure() {
    }

    @Test
    public void executeSql() {
        String sql = "delete from person where id = '308bdb77f15d476ba767414aed8b38d7'";
        int count = personService.executeSql(sql);
        System.out.println(count);

    }

    @Test
    public void findMapBySql() {
    }

    @Test
    public void findMapBySql1() {
    }

    @Test
    public void findMapBySql2() {
    }

    @Test
    public void findMapBySql3() {
    }

    @Test
    public void findMapBySql4() {
    }

    @Test
    public void findMapBySql5() {
    }

    @Test
    public void countBySql2() {
    }

    @Test
    public void findMap() {
    }

    @Test
    public void getListByCriteria() {
    }

    @Test
    public void getListByCriteria1() {
    }

    @Test
    public void findByCriteria() {
    }

    @Test
    public void getCountByCriteria() {
    }

    @Test
    public void findByExample() {
    }

    @Test
    public void findByExample1() {
    }
}