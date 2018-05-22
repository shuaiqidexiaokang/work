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

/**
 * 分配方式二：使用流程变量指定办理人
 */
public class l_PersonTask1 {
    /*
    * 个人任务及三种分配方式：
    1：在l_PersonTask.bpmn中直接写 assignee=“张三丰"
    2：在l_PersonTask.bpmn中写 assignee=“${userID}”，变量的值要是String的。
         使用流程变量指定办理人
    3，使用TaskListener接口，要使类实现该接口，在类中定义：
         delegateTask.setAssignee(assignee);// 指定个人任务的办理人

    使用任务ID和办理人重新指定办理人：
    processEngine.getTaskService()//
                           .setAssignee(taskId, userId);
    * */
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    @Test
    public void deploymentProcessDefinition() {
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream("/diagrams/l_PersonTask1.bpmn");
        InputStream inputStreamPng = this.getClass().getResourceAsStream("/diagrams/l_PersonTask1.png");
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("分配方式二：使用流程变量指定办理人")
                .addInputStream("l_PersonTask1.bpmn",inputStreamBpmn)
                .addInputStream("l_PersonTask1.png",inputStreamPng)
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    //启动流程实例同时添加流程变量
    @Test
    public void startProcessInstance() {
        String processInstanceByKey = "personTaskProcess";
        Map<String,Object> variables = new HashMap<>();
        variables.put("userId","周芷若");
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processInstanceByKey,variables);
        System.out.println("流程实例ID:" + processInstance.getId());
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());
    }

    //查询当前个人任务
    @Test
    public void findMyPersonalTask() {
        String assignee = "周芷若";
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
        String taskId = "12509";
        processEngine.getTaskService()
                .complete(taskId);
        System.out.println("完成任务：任务id：" + taskId);
    }

}
