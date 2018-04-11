package com.lzk.relationship.o2o_fk;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import java.util.Date;

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
        IdCard idCard = new IdCard("11111111111111","qq");
        Student student = new Student(idCard,"11",new Date(),"11");
        session.save(idCard);
        session.save(student);

        transaction.commit();
    }

}