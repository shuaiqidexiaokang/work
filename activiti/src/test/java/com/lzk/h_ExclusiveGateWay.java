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

public class h_ExclusiveGateWay {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    @Test
    public void deploymentProcessDefinition() {
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream("/diagrams/h_exclusiveGateWay.bpmn");
        InputStream inputStreamPng = this.getClass().getResourceAsStream("/diagrams/h_exclusiveGateWay.png");
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("排他网关")
                .addInputStream("h_exclusiveGateWay.bpmn",inputStreamBpmn)
                .addInputStream("exclusiveGateWay.png",inputStreamPng)
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    //启动流程实例
    @Test
    public void startProcessInstance() {
        String processInstanceByKey = "exclusiveGateWay";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processInstanceByKey);
        System.out.println("流程实例ID:" + processInstance.getId());
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());
    }

    //查询当前个人任务
    @Test
    public void findMyPersonalTask() {
        String assignee = "包南洋";
        List<Task> list = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(assignee)
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
        String taskId = "132504";
        Map<String,Object> variables = new HashMap<>();
        variables.put("money",400);
        processEngine.getTaskService()
                .complete(taskId,variables);
        System.out.println("完成任务：任务id：" + taskId);
    }
}
