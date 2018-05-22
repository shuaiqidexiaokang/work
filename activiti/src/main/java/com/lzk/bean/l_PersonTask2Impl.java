package com.lzk.bean;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class l_PersonTask2Impl implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String assignee = "灭绝师太";
        delegateTask.setAssignee(assignee);
    }
}
