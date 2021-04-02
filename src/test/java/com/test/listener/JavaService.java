package com.test.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

// 用于ServiceTask的任务
public class JavaService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println(delegateExecution.getEventName());
        System.out.println(delegateExecution.getId());
    }
}
