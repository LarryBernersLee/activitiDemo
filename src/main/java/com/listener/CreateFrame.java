package com.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;
@Component("CreateFrame")
public class CreateFrame implements TaskListener {
 
	
	@Override
	public void notify(DelegateTask delegateTask) {
		String procinstId = delegateTask.getVariable("procinstId").toString();
		delegateTask.setVariable("procinstId", procinstId);
		System.out.println("CreateFrame");
	}

}
