package com.lzk;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

public class d_ProcessInstance {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    /*
    *
        #流程实例，执行对象，任务
        select * from act_ru_execution;			#正在执行的执行对象表

        select * from act_hi_procinst;			#流程实例历史表

        select * from act_ru_task;			    #正在执行的任务表

        select * from act_hi_taskinst;			#任务历史表

        select * from act_hi_actinst;	        #所有活动节点的历史表
    * */

    //部署流程定义（从zip）
    @Test
    public void deploymentProcessDefinition_zip() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/a_helloworld.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);

        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name("流程定义")
                .addZipInputStream(zipInputStream)//指定zip格式的文件完成部署
                .deploy();//完成部署
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
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
        String taskId = "20004";
        processEngine.getTaskService()//与正在执行的任务管理相关的Service
                .complete(taskId);
        System.out.println("完成任务：任务id：" + taskId);
    }

    //查询流程状态（判断流程正在执行，还是结束）
    @Test
    public void isProcessEnd() {
        String processInstanceId = "20001";
        ProcessInstance processInstance = processEngine.getRuntimeService()//与正在执行的任务管理相关的Service
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        if (processInstance == null){
            System.out.println("流程已结束");
        }else{
            System.out.println("流程未结束");
        }
    }

    //查询历史任务
    @Test
    public void findHistoryTask() {
        String taskAssignee = "张三";
        List<HistoricTaskInstance> list = processEngine.getHistoryService()//与历史数据相关的Service
                .createHistoricTaskInstanceQuery()
                .taskAssignee(taskAssignee)
                .list();
        if (list != null && list.size() > 0) {
            for (HistoricTaskInstance ht :list){
                System.out.println("历史任务ID：" + ht.getId());
                System.out.println("历史任务名称：" + ht.getName());
                System.out.println("历史任务流程实例ID：" + ht.getProcessDefinitionId());
                System.out.println("历史任务受理人：" + ht.getAssignee());
                System.out.println("历史任务开始时间：" + ht.getStartTime());
                System.out.println("历史任务结束时间：" + ht.getEndTime());
                System.out.println("历史任务所用时间：" + ht.getDurationInMillis());
                System.out.println("####################################");
            }
        }
    }

    //查询历史流程实例
    @Test
    public void findHistoryProcessInstance() {
        String processInstanceId = "20001";
        HistoricProcessInstance hpi = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        System.out.println("历史流程实例ID：" + hpi.getId());
        System.out.println("流程定义ID：" + hpi.getProcessDefinitionId());
        System.out.println("开始时间：" + hpi.getStartTime());
        System.out.println("结束时间：" + hpi.getEndTime());
        System.out.println("所用时间：" + hpi.getDurationInMillis());

    }
}
