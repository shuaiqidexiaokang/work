package com.lzk;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class b_HelloWorld {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    @Test
    public void deploymentProcessDefinition() {
        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name("helloworld入门程序")
                .addClasspathResource("diagrams/a_helloworld.png")//从classpath的资源中加载，一次只能加载一个文件
                .addClasspathResource("diagrams/a_helloworld.bpmn")//从classpath的资源中加载，一次只能加载一个文件
                .deploy();//完成部署
        System.out.println(deployment.getId());//1
        System.out.println(deployment.getName());//helloworld入门程序

    }

    //启动流程实例
    @Test
    public void startProcessInstance() {
        //流程定义的key
        String processInstanceByKey = "helloworld";
        //与正在执行的流程实例和执行对象相关的Service
        ProcessInstance processInstance = processEngine.getRuntimeService()
                //使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认按照最新版本的流程定义启动
                .startProcessInstanceByKey(processInstanceByKey);
        System.out.println("流程实例ID:" + processInstance.getId());//101
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());//helloworld:1:4

    }

    //查询当前个人任务
    @Test
    public void findMyPersonalTask() {
        String assignee = "张三";
        List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
                .createTaskQuery()//创建任务查询对象
                .taskAssignee(assignee)//指定个人任务查询，指定办理人
                .list();
        if (list != null && list.size() > 0) {
            for (Task task :list){
                System.out.println("任务ID：" + task.getId());//2504
                System.out.println("任务名称：" + task.getName());//提交申请
                System.out.println("任务创建时间：" + task.getCreateTime());//Wed May 16 19:02:24 CST 2018
                System.out.println("任务办理人：" + task.getAssignee());//张三
                System.out.println("流程实例ID：" + task.getProcessInstanceId());//2501
                System.out.println("执行对象ID：" + task.getExecutionId());//2501
                System.out.println("流程定义ID：" + task.getProcessDefinitionId());//helloworld:1:4
            }
        }
    }

    //完成我的任务
    @Test
    public void completeMyPersonalTask() {
        String taskId = "2504";
        processEngine.getTaskService()//与正在执行的任务管理相关的Service
                .complete(taskId);
        System.out.println("完成任务：任务id：" + taskId);
    }
}
