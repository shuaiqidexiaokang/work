package com.lzk;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.junit.Test;

import java.util.List;

public class f_HistoryQuery {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


    //查询历史流程实例
    @Test
    public void findHistoryProcessInstance(){
        String processInstanceId = "45001";
        HistoricProcessInstance hpi = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        System.out.println("历史流程ID：" + hpi.getId());
        System.out.println("历史流程定义ID：" + hpi.getProcessDefinitionId());
        System.out.println("历史流程开始时间：" + hpi.getStartTime());
        System.out.println("历史流程结束时间：" + hpi.getEndTime());
        System.out.println("历史流程持续时间：" + hpi.getDurationInMillis());
    }

    //查询历史活动
    @Test
    public void findHistoryActiviti(){
        String processInstanceId = "45001";
        List<HistoricActivityInstance> list = processEngine.getHistoryService()
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();
        if (list != null && list.size() > 0) {
            for (HistoricActivityInstance hai :list){
                System.out.println("历史活动ID：" + hai.getId());
                System.out.println("历史活动流程定义ID：" + hai.getProcessDefinitionId());
                System.out.println("历史活动流程实例ID：" + hai.getProcessInstanceId());
                System.out.println("历史活动执行ID：" + hai.getExecutionId());
                System.out.println("历史活动ID：" + hai.getActivityId());
                System.out.println("历史活动任务ID：" + hai.getTaskId());
                System.out.println("历史活动名称：" + hai.getActivityName());
                System.out.println("历史活动类型：" + hai.getActivityType());
                System.out.println("历史活动受理人：" + hai.getAssignee());
                System.out.println("历史活动开始时间：" + hai.getStartTime());
                System.out.println("历史活动结束时间：" + hai.getEndTime());
                System.out.println("历史活动持续时间：" + hai.getDurationInMillis());
                System.out.println("####################################");
            }
        }
    }

    //查询历史任务
    @Test
    public void findHistoryTask() {
        String processInstanceId = "45001";
        List<HistoricTaskInstance> list = processEngine.getHistoryService()//与历史数据相关的Service
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .asc()
                .list();
        if (list != null && list.size() > 0) {
            for (HistoricTaskInstance ht :list){
                System.out.println("历史任务ID：" + ht.getId());
                System.out.println("历史任务流程定义ID：" + ht.getProcessDefinitionId());
                System.out.println("历史任务定义键：" + ht.getTaskDefinitionKey());
                System.out.println("历史任务流程实例ID：" + ht.getProcessInstanceId());
                System.out.println("历史任务执行ID：" + ht.getExecutionId());
                System.out.println("历史任务名称：" + ht.getName());
                System.out.println("历史任务受理人：" + ht.getAssignee());
                System.out.println("历史任务开始时间：" + ht.getStartTime());
                System.out.println("历史任务结束时间：" + ht.getEndTime());
                System.out.println("历史任务持续时间：" + ht.getDurationInMillis());
                System.out.println("####################################");
            }
        }
    }

    //查询历史流程变量
    @Test
        public void findHistoryProcessVariables() {
        String processInstanceId = "45001";
        List<HistoricVariableInstance> list = processEngine.getHistoryService()
                .createHistoricVariableInstanceQuery()
                .processInstanceId(processInstanceId)
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
