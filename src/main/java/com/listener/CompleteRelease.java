package com.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component("CompleteRelease")
public class CompleteRelease implements TaskListener {
 
  private static final long serialVersionUID = 1L;

  @Override
  public void notify(DelegateTask delegateTask) {
	  String procinstId = delegateTask.getVariable("procinstId").toString();
		delegateTask.setVariable("procinstId", procinstId);
		System.out.println("CompleteRelease");
  }

}
