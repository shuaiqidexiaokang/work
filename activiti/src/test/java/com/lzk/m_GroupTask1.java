package com.lzk;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 组任务及三种分配方式：
 1：在taskProcess.bpmn中直接写 candidate-users=“小A,小B,小C,小D"
 2：在taskProcess.bpmn中写 candidate-users =“#{userIDs}”，变量的值要是String的。
 使用流程变量指定办理人
 Map<String, Object> variables = new HashMap<String, Object>();
 variables.put("userIDs", "大大,小小,中中");
 3，使用TaskListener接口，使用类实现该接口，在类中定义：
 //添加组任务的用户
 delegateTask.addCandidateUser(userId1);
 delegateTask.addCandidateUser(userId2);
 组任务分配给个人任务（认领任务）：
 processEngine.getTaskService().claim(taskId, userId);
 个人任务分配给组任务：
 processEngine.getTaskService(). setAssignee(taskId, null);
 向组任务添加人员：
 processEngine.getTaskService().addCandidateUser(taskId, userId);
 向组任务删除人员：
 processEngine.getTaskService().deleteCandidateUser(taskId, userId);
 个人任务和组任务存放办理人对应的表：
 act_ru_identitylink表存放任务的办理人，包括个人任务和组任务，表示正在执行的任务
 act_hi_identitylink表存放任务的办理人，包括个人任务和组任务，表示历史任务
 区别在于：如果是个人任务TYPE的类型表示participant（参与者）
 如果是组任务TYPE的类型表示candidate（候选者）和participant（参与者）
 */
public class m_GroupTask1 {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程定义
    @Test
    public void deploymentProcessDefinition() {
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream("/diagrams/m_GroupTask1.bpmn");
        InputStream inputStreamPng = this.getClass().getResourceAsStream("/diagrams/m_GroupTask1.png");
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("分配组任务方式一：直接指定办理人：")
                .addInputStream("m_GroupTask1.bpmn",inputStreamBpmn)
                .addInputStream("m_GroupTask1.png",inputStreamPng)
                .deploy();
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
    }

    //启动流程实例
    @Test
    public void startProcessInstance() {
        String processInstanceByKey = "groupTaskProcess";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processInstanceByKey);
        System.out.println("流程实例ID:" + processInstance.getId());
        System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId());
    }

    //查询当前个人任务
    @Test
    public void findMyPersonalTask() {
        String candidateUser = "小A";
        List<Task> list = processEngine.getTaskService()
                .createTaskQuery()
                .taskCandidateUser(candidateUser)
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
        String taskId = "42504";
        processEngine.getTaskService()
                .complete(taskId);
        System.out.println("完成任务：任务id：" + taskId);
    }

    //查询正在执行的任务办理人
    @Test
    public void findRunPersonTask() {
        String taskId = "42504";
        List<IdentityLink> list = processEngine.getTaskService()
                .getIdentityLinksForTask(taskId);
        if (list != null && list.size() > 0) {
            for (IdentityLink it :list){
                System.out.println("组任务ID：" + it.getGroupId());
                System.out.println("类型：" + it.getType());
                System.out.println("用户ID：" + it.getUserId());
                System.out.println("任务ID：" + it.getTaskId());
                System.out.println("流程实例ID：" + it.getProcessInstanceId());
                System.out.println("流程定义ID：" + it.getProcessDefinitionId());
                System.out.println("##########################");
            }
        }
    }

    //查询历史的任务办理人
    @Test
    public void findHistoryPersonTask() {
        String processInstance = "42501";
        List<HistoricIdentityLink> list = processEngine.getHistoryService()
                .getHistoricIdentityLinksForProcessInstance(processInstance);
        if (list != null && list.size() > 0) {
            for (HistoricIdentityLink it :list){
                System.out.println("组任务ID：" + it.getGroupId());
                System.out.println("类型：" + it.getType());
                System.out.println("用户ID：" + it.getUserId());
                System.out.println("任务ID：" + it.getTaskId());
                System.out.println("流程实例ID：" + it.getProcessInstanceId());
                System.out.println("##########################");
            }
        }
    }

    //拾取任务，将组任务分给个人，指定任务的办理人字段
    @Test
    public void claim() {
        String taskId = "42504";
        //分配给个人任务，可以是组成员，也可以是非组成员
        String userId = "大F";
        processEngine.getTaskService()
                .claim(taskId,userId);
    }

    //将个人任务回退到组任务，前提，之前一定是一个组任务
    @Test
    public void setAssigne() {
        String taskId = "42504";
        processEngine.getTaskService()
                .setAssignee(taskId,null);
    }

    //向组任务添加成员
    @Test
    public void addGroupUser() {
        String taskId = "42504";
        String userId = "大F";
        processEngine.getTaskService()
                .addCandidateUser(taskId,userId);
    }

    //向组任务删除成员
    @Test
    public void deleteGroupUser() {
        String taskId = "42504";
        String userId = "大F";
        processEngine.getTaskService()
                .deleteCandidateUser(taskId,userId);
    }
}
