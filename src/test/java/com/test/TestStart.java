package com.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;

public class TestStart extends BaseTestCase {
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	TaskService taskService;
	
	
	@Test
	public void start() {
		Long businessKey = new Double(1000000 * Math.random()).longValue();
		System.out.println("businessKey:"+businessKey);
		//ProcessInstance s=runtimeService.startProcessInstanceById("myProcess:2:2504", businessKey.toString());
		
		Map<String, Object> variables = new HashMap<String,Object>();
		variables.put("inputUser", "zhangchangyong");//表示惟一用户
		runtimeService.startProcessInstanceById("Import-video:1:4", variables);
 
 
		long count = runtimeService.createProcessInstanceQuery().count();  
		System.out.println("流程实例数量：" + count);  
	}
}
