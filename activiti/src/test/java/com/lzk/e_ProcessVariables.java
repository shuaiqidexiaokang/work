package com.lzk;

import com.lzk.bean.Person;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class e_ProcessVariables {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    /*
//        //从classpath根目录下加载指定名称的文件
//        InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/a_helloworld.zip");
//        //从当前包下加载指定名称的文件 有点问题
//        InputStream in = this.getClass().getResourceAsStream("a_helloworld.zip");
//        //从classpath根目录下加载指定的文件
//        InputStream in = this.getClass().getResourceAsStream("/diagrams/a_helloworld.zip");
    * */
    //部署流程定义（使用资源文件加载）
    @Test
    public void deploymentProcessDefinition_inputStream() {
        InputStream inputStreambpmn = this.getClass().getResourceAsStream("/diagrams/e_processVariables.bpmn");
        InputStream inputStreampng = this.getClass().getResourceAsStream("/diagrams/e_processVariables.png");
        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name("流程定义")//添加部署名称
                .addInputStream("e_processVariables.bpmn",inputStreambpmn)//使用资源文件的名称
                .addInputStream("e_processVariables.png",inputStreampng)//使用资源文件的名称
                .deploy();//完成部署
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    //启动流程实例
    @Test
    public void startProcessInstance() {
        //流程定义的key
        String processInstanceByKey = "processVariables";
        //与正在执行的流程实例和执行对象相关的Service
        ProcessInstance processInstance = processEngine.getRuntimeService()
                //使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认按照最新版本的流程定义启动
                .startProcessInstanceByKey(processInstanceByKey);
        System.out.println("流程实例ID:" + processInstance.getId());//101
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());//helloworld:1:4
    }

    //设置流程变量
    @Test
    public void setVariables() {
        TaskService taskService = processEngine.getTaskService();
        String taskId = "45004";
        /*1：设置流程变量，使用基本数据类型*/
//        taskService.setVariableLocal(taskId,"请假天数",3);//与任务ID绑定
//        taskService.setVariable(taskId,"请假日期",new Date());
//        taskService.setVariable(taskId,"请假原因","回家探亲");
        /*2：设置流程变量，使用javaBean类型*/
        Person person = new Person(1,"翠花");
        taskService.setVariable(taskId,"人员信息",person);
        System.out.println("设置流程变量成功！");
    }

    //获取流程变量
    @Test
    public void getVariables() {
        TaskService taskService = processEngine.getTaskService();
        String taskId = "45004";
        /*1：获取流程变量，使用基本数据类型*/
//        Integer days = (Integer) taskService.getVariable(taskId,"请假天数");
//        Date date = (Date) taskService.getVariable(taskId,"请假日期");
//        String resean = (String) taskService.getVariable(taskId,"请假原因");
//        System.out.println("请假天数：" + days);
//        System.out.println("请假日期：" + date);
//        System.out.println("请假原因：" + resean);
        /*2：获取流程变量，使用javaBean类型*/
        Person person = (Person) taskService.getVariable(taskId,"人员信息");
        System.out.println(person);
    }

    //模拟设置和获取流程变量的场景
    @Test
    public void setAndGetVariables() {
        /*与流程实例，执行对象（正在执行）相关的Servise*/
        RuntimeService runtimeService = processEngine.getRuntimeService();
        /*与任务（正在执行）相关的Servise*/
        TaskService taskService = processEngine.getTaskService();

        /*设置流程变量*/
//        //表示使用执行对象ID，和流程变量的名称，设置流程变量的值（一次只能设置一个值）
//        runtimeService.setVariable(executionId,ariableName,value);
//        //表示使用执行对象ID，和Map集合设置流程变量，map集合的key就是流程变量的名称，map集合的value就是流程变量的值（一次只能设置多个值）
//        runtimeService.setVariables(executionId,ariables);
//
//        //表示使用任务ID，和流程变量的名称，设置流程变量的值（一次只能设置一个值）
//        taskService.setVariable(taskId,ariableName,value);
//        //表示使用任务ID，和Map集合设置流程变量，map集合的key就是流程变量的名称，map集合的value就是流程变量的值（一次只能设置多个值）
//        taskService.setVariables(taskId,ariables);

//        //启动流程实例的同时，可以设置流程变量，用Map集合
//        runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
//        //完成任务的同时，设置流程变量，用Map集合
//        taskService.complete(taskId,variables);

        /*获取流程变量*/
//        //使用执行对象ID和流程变量的名称，获取流程变量的值
//        runtimeService.getVariable(executionId,ariableName);
//        //使用执行对象ID，获取所有的流程变量，将流程变量的放置到Map集合中，map集合的key就是流程变量的名称，map集合的value就是流程变量的值（一次只能设置多个值）
//        runtimeService.getVariables(executionId);
//        //使用执行对象ID，获取流程变量的值，通过设置流程变量的名称存放到集合中，获取指定流程变量名称的值，值存放到Map集合中
//        runtimeService.getVariables(executionId,ariableNames);
//
//        //使用任务ID和流程变量的名称，获取流程变量的值
//        taskService.getVariable(taskId,ariableName);
//        //使用任务ID，获取所有的流程变量，将流程变量的放置到Map集合中，map集合的key就是流程变量的名称，map集合的value就是流程变量的值（一次只能设置多个值）
//        taskService.getVariables(taskId);
//        //使用任务ID，获取流程变量的值，通过设置流程变量的名称存放到集合中，获取指定流程变量名称的值，值存放到Map集合中
//        taskService.getVariables(taskId,ariableNames);
    }

    //完成我的任务
    @Test
    public void completeMyPersonalTask() {
        String taskId = "52502";
        processEngine.getTaskService()//与正在执行的任务管理相关的Service
                .complete(taskId);
        System.out.println("完成任务：任务id：" + taskId);
    }

    //查询流程变量的历史表
    @Test
    public void findHistoryProcessVariables() {
        List<HistoricVariableInstance> list = processEngine.getHistoryService()
                .createHistoricVariableInstanceQuery()
                .variableName("请假天数")
                .list();
        if (list != null && list.size() > 0) {
            for (HistoricVariableInstance hvt :list){
                System.out.println("历史流程变量ID：" + hvt.getId());
                System.out.println("历史流程实例ID：" + hvt.getProcessInstanceId());
                System.out.println("历史流程任务ID：" + hvt.getTaskId());
                System.out.println("历史流程变量类型：" + hvt.getVariableTypeName());
                System.out.println("历史流程变量名称：" + hvt.getVariableName());
                System.out.println("历史流程变量值：" + hvt.getValue());
                System.out.println("历史流程变量创建时间：" + hvt.getCreateTime());
                System.out.println("历史流程变量更新时间：" + hvt.getLastUpdatedTime());
                System.out.println("####################################");
            }
        }
    }
}
