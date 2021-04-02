package com.test.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;


public class InstanceListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) {
        System.out.println("�����������");
        System.out.println(delegateExecution.getEventName());
        System.out.println(delegateExecution.getProcessInstanceId());
        System.out.println(delegateExecution.getId());
    }
}
