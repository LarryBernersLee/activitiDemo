package com.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author zhaowenhe
 *
 */
@SuppressWarnings("serial")
@Component("CreateRelease")
public class CreateRelease implements TaskListener {
	private static final Logger logger = Logger.getLogger(CreateRelease.class);
 
 
	@Override
	public void notify(DelegateTask delegateTask) {
		Object id = delegateTask.getVariable("procinstId");
		if (id != null) {
			String procinstId = id.toString();
			delegateTask.setVariable("procinstId", procinstId);
		}
		System.out.println("CreateRelease");
 
	}
}
