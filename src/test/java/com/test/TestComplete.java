package com.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;

public class TestComplete extends BaseTestCase {
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	TaskService taskService;

	@Test
	public void testCompleteTask() {
		String taskId = "47502 ";
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("outcome", "驳回");
		//variables.put("inputUser", "lisi");//表示惟一用户
		// 3：使用任务ID，完成当前人的个人任务，同时流程变量
		taskService.complete(taskId,variables);
		System.out.println("任务ID：" + taskId + "执行完成");
	}

}
