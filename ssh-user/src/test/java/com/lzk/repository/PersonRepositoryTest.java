package com.lzk.repository;

import junit.framework.TestCase;


import com.lzk.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PersonRepositoryTest extends TestCase {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void save() {
        Person person = new Person();
        person.setUsername("XRog");
        person.setPhone("18381005946");
        person.setAddress("chenDu");
        person.setRemark("this is XRog");
        Long count = personRepository.save(person);
        System.out.println(count);
    }

    @Test
    public void delete() {
        personRepository.delete(1L);
    }

    @Test
    public void saveOrUpdate() {
        Person person = new Person();
        person.setId(1L);
        person.setUsername("XRog11");
        person.setPhone("1838100594611");
        person.setAddress("chenDu11");
        person.setRemark("this is XRog11");
        personRepository.saveOrUpdate(person);
    }

    @Test
    public void findAll() {
        List<Person> persons = personRepository.findAll();
        persons.forEach(System.out::println);
    }

    @Test
    public void get() {
        Person person = personRepository.get(2L);
        System.out.println(person);
    }
}