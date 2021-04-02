package com.test.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;


// 设置业务监视器
public class ProcessListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("enter the listener");
        System.out.println("taskId: " + delegateTask.getId());
        System.out.println("taskName: " + delegateTask.getName());
        System.out.println("eventName: " + delegateTask.getEventName());
        delegateTask.setAssignee("user1");
    }
}
