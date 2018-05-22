package com.lzk;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class j_StartEnd {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    @Test
    public void deploymentProcessDefinition() {
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream("/diagrams/j_startEnd.bpmn");
        InputStream inputStreamPng = this.getClass().getResourceAsStream("/diagrams/j_startEnd.png");
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("开始活动")
                .addInputStream("j_startEnd.bpmn",inputStreamBpmn)
                .addInputStream("j_startEnd.png",inputStreamPng)
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    //启动流程实例+判断流程是否结束+查询历史
    @Test
    public void startProcessInstance() {
        String processInstanceByKey = "startEnd";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processInstanceByKey);
        System.out.println("流程实例ID:" + processInstance.getId());
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());
        /*判断流程是否结束，查询正在执行的执行对象*/
        Execution execution = processEngine.getRuntimeService()
                .createExecutionQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        if (execution == null){
            HistoricProcessInstance hpi = processEngine.getHistoryService()
                    .createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstance.getId())
                .singleResult();
            System.out.println(hpi.getId() + "\t" + hpi.getStartTime() +"\t" + hpi.getEndTime() +"\t" + hpi.getDurationInMillis());
        }
    }

    //查询当前个人任务
    @Test
    public void findMyPersonalTask() {
        String assignee = "商家";
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
        String taskId = "7502";
        processEngine.getTaskService()
                .complete(taskId);
        System.out.println("完成任务：任务id：" + taskId);
    }
}
