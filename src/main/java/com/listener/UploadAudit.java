package com.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component("UploadAudit")
public class UploadAudit implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		Object id = delegateTask.getVariable("procinstId");
		if(id != null) {
			String procinstId = id.toString();
			delegateTask.setVariable("procinstId", procinstId);
		}
		System.out.println("CreateTranscoding");
	}

}
