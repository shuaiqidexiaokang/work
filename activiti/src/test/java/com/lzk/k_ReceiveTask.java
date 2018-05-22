package com.lzk;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.io.InputStream;

public class k_ReceiveTask {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    @Test
    public void deploymentProcessDefinition() {
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream("/diagrams/k_receiveTask.bpmn");
        InputStream inputStreamPng = this.getClass().getResourceAsStream("/diagrams/k_receiveTask.png");
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("接收活动任务")
                .addInputStream("k_receiveTask.bpmn",inputStreamBpmn)
                .addInputStream("k_receiveTask.png",inputStreamPng)
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    //启动流程实例 + 设置流程变量 + 向后执行流程 + 获取流程变量 + 向后执行流程
    @Test
    public void startProcessInstance() {
        String processInstanceByKey = "receiveTask";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processInstanceByKey);
        System.out.println("流程实例ID:" + processInstance.getId());
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());

        /*查询执行对象*/
        Execution execution1 = processEngine.getRuntimeService()
                .createExecutionQuery()
                .processInstanceId(processInstance.getId())
                .activityId("receivetask1")//当前活动ID，对应receiveTask.bpmn文件中的活动节点id属性的值
                .singleResult();

        /*使用流程变量设置当日销售额，用来传递业务参数*/
        processEngine.getRuntimeService()
                .setVariable(execution1.getId(),"汇总每日销售额",21000);

        /*向后执行一步，如果流程处于等待状态，使流程继续执行*/
        processEngine.getRuntimeService()
                .signal(execution1.getId());

        /*查询执行对象*/
        Execution execution2 = processEngine.getRuntimeService()
                .createExecutionQuery()
                .processInstanceId(processInstance.getId())
                .activityId("receivetask2")
                .singleResult();

        /*从流程变量获取当日销售额*/
        Integer value = (Integer) processEngine.getRuntimeService()
                .getVariable(execution2.getId(),"汇总每日销售额");

        System.out.println("给老板发送短信，金额是：" + value);

        /*向后执行一步，如果流程处于等待状态，使流程继续执行*/
        processEngine.getRuntimeService()
                .signal(execution2.getId());
    }
}
