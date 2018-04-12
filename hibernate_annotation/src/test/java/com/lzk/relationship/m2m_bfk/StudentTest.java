package com.lzk.relationship.m2m_bfk;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void testShemaExport(){
        //创建hibernate配置文件
        Configuration configuration = new Configuration().configure();
        //创建服务注册对象
        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        //创建SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        SchemaExport schemaExport = new SchemaExport(configuration);
        schemaExport.create(true,true);
    }

    @Test
    public void testAddStudent() {
        //创建hibernate配置文件
        Configuration configuration = new Configuration().configure();
        //创建服务注册对象
        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        //创建SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student s1 = new Student("男",new Date(),"计算机");
        Student s2 = new Student("女",new Date(),"计算机");
        Student s3 = new Student("男",new Date(),"电子");
        Student s4 = new Student("女",new Date(),"电子");

        Teacher t1 = new Teacher("T001","张老师");
        Teacher t2 = new Teacher("T002","王老师");
        Teacher t3 = new Teacher("T003","陈老师");
        Teacher t4 = new Teacher("T004","林老师");

        Set<Teacher> set1 = new HashSet<>();
        set1.add(t1);
        set1.add(t2);
        s1.setTeachers(set1);

        Set<Teacher> set2 = new HashSet<>();
        set2.add(t3);
        set2.add(t4);
        s2.setTeachers(set2);

        Set<Teacher> set3 = new HashSet<>();
        set3.add(t1);
        set3.add(t3);
        s3.setTeachers(set3);

        Set<Teacher> set4 = new HashSet<>();
        set4.add(t2);
        set4.add(t4);
        s4.setTeachers(set4);

        session.save(t1);
        session.save(t2);
        session.save(t3);
        session.save(t4);
        session.save(s1);
        session.save(s2);
        session.save(s3);
        session.save(s4);

        transaction.commit();
    }
}