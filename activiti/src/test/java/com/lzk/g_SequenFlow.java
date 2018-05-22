package com.lzk;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class g_SequenFlow {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    @Test
    public void deploymentProcessDefinition() {
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream("/diagrams/g_sequenFlow.bpmn");
        InputStream inputStreamPng = this.getClass().getResourceAsStream("/diagrams/g_sequenFlow.png");
        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name("连线")
                .addInputStream("g_sequenFlow.bpmn",inputStreamBpmn)
                .addInputStream("g_sequenFlow.png",inputStreamPng)
                .deploy();//完成部署
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    //启动流程实例
    @Test
    public void startProcessInstance() {
        //流程定义的key
        String processInstanceByKey = "sequenFlow";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processInstanceByKey);
        System.out.println("流程实例ID:" + processInstance.getId());//101
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());//helloworld:1:4
    }

    //查询当前个人任务
    @Test
    public void findMyPersonalTask() {
        String assignee = "赵六";
        List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
                .createTaskQuery()//创建任务查询对象
                .taskAssignee(assignee)//指定个人任务查询，指定办理人
                .list();
        if (list != null && list.size() > 0) {
            for (Task task :list){
                System.out.println("任务ID：" + task.getId());
                System.out.println("任务名称：" + task.getName());
                System.out.println("任务创建时间：" + task.getCreateTime());
                System.out.println("任务办理人：" + task.getAssignee());
                System.out.println("流程实例ID：" + task.getProcessInstanceId());
                System.out.println("执行对象ID：" + task.getExecutionId());
                System.out.println("流程定义ID：" + task.getProcessDefinitionId());
            }
        }
    }

    //完成我的任务
    @Test
    public void completeMyPersonalTask() {
        String taskId = "85003";
        //完成任务的同时，设置流程变量，使用流程变量用来指定完成任务后，下一个连线，对应sequenFlow.bpmn文件中的${message=='不重要'}
        Map<String,Object> variables = new HashMap<>();
        variables.put("message","重要");
        processEngine.getTaskService()//与正在执行的任务管理相关的Service
                .complete(taskId,variables);
        System.out.println("完成任务：任务id：" + taskId);
    }
}
