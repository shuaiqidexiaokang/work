package com.lzk.clazz_attribute.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import java.util.Date;

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
        Address address = new Address("aaaa","bbb","111");
        Student student = new Student(22,"111","1111",new Date(),"222",address);
        session.save(student);

        transaction.commit();
    }
}