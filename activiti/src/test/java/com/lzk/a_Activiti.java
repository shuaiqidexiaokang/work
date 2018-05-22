package com.lzk;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class a_Activiti {
    //使用代码创建工作流需要的23张表
    @Test
    public void createTable(){
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        //连接数据库的配置
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/activiti?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("");
        /**
         * public static final String DB_SCHEMA_UPDATE_FALSE = "false";不能自动创建表，需要表的存在
           public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";先删除表在创建表
           public static final String DB_SCHEMA_UPDATE_TRUE = "true";若果表不存在，自动创建表
         */
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //工作流的核心对象，ProcessEngine对象
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println("ProcessEngine:" + processEngine);
    }

    //使用配置文件创建工作流需要的23张表
    @Test
    public void createTable2(){
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
        System.out.println("ProcessEngine:" + processEngine);
    }
}
