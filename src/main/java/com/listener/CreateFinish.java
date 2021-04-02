package com.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component("CreateFinish")
public class CreateFinish implements ExecutionListener {
	@Override
	public void notify(DelegateExecution execution) {
		System.out.println("CreateFinish");
	}
}
