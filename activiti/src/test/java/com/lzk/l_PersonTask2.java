package com.lzk;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 分配方式三：使用类指定办理人
 * 使用TaskListener接口，要使类实现该接口，在类中定义：
    delegateTask.setAssignee(assignee);// 指定个人任务的办理人
 */
public class l_PersonTask2 {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    @Test
    public void deploymentProcessDefinition() {
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream("/diagrams/l_PersonTask2.bpmn");
        InputStream inputStreamPng = this.getClass().getResourceAsStream("/diagrams/l_PersonTask2.png");
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("分配方式三：使用类指定办理人")
                .addInputStream("l_PersonTask2.bpmn",inputStreamBpmn)
                .addInputStream("l_PersonTask2.png",inputStreamPng)
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    //启动流程实例
    @Test
    public void startProcessInstance() {
        String processInstanceByKey = "personTask2Process";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processInstanceByKey);
        System.out.println("流程实例ID:" + processInstance.getId());
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());
    }

    //查询当前个人任务
    @Test
    public void findMyPersonalTask() {
        String assignee = "灭绝师太";
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
        String taskId = "32504";
        processEngine.getTaskService()
                .complete(taskId);
        System.out.println("完成任务：任务id：" + taskId);
    }

    //可以分配个人任务从一个人到另一个人（认领任务）
    @Test
    public void setAssigneeTask() {
        //任务ID
        String taskId = "32504";
        //指定认领的办理者
        String userId = "周芷若";
        processEngine.getTaskService()
                .setAssignee(taskId,userId);
    }

    }
