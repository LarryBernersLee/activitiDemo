package com.aoyuntech.yunling.activiti;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/main")
public class mainController {
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	TaskService taskService;

	@RequestMapping("/start")
	@ResponseBody
	public Map<String, String> navbarList(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		System.out.println("进入start");

		String procinstId = System.currentTimeMillis() + "";
		Map<String, Object> variables = new HashMap<String, Object>();
		// variables.put("inputUser", "zhangchangyong");//表示惟一用户
		// runtimeService.startProcessInstanceById("Import-video:1:4", variables);
		variables.put("procinstId", procinstId);
		// variables.put("userName", "zhangchangyong_auto");
		variables.put("userName", null);
		runtimeService.startProcessInstanceByKey("Import-video-audit", procinstId, variables);

		long count = runtimeService.createProcessInstanceQuery().count();
		System.out.println("流程实例数量：" + count);

		Map<String, String> map = new HashMap<String, String>();
		map.put("info", "success");
		return map;
	}

	@RequestMapping("/complete")
	@ResponseBody
	public Map<String, String> complete(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		System.out.println("进入complete");
		String businessId = request.getParameter("businessId");
		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessId)
				.list();
		if (list.size() > 0 && list != null) {
			ProcessInstance pi = list.get(0);
			String processInstanceid = pi.getProcessInstanceId();
			Task task = taskService.createTaskQuery().processInstanceId(processInstanceid).singleResult();
			String taskId = task.getId();
			Map<String, Object> variables = new HashMap<String, Object>();
			// variables.put("objectId",businessId);
			variables.put("userName", "zhangchangyong_auto");
			variables.put("message", "1");
			taskService.complete(taskId, variables);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("info", "success");
		return map;
	}

	@GetMapping("/deploy")
	@ResponseBody
	public boolean deploy(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String path = "F:\\code\\ActivitiSpring\\src\\main\\resources\\info.bpmn";
		FileInputStream fileInputStream = new FileInputStream(path);
		String resPath  = "info.bpmn";
		Deployment deployment = repositoryService.createDeployment()
			  .addClasspathResource(resPath)
				.name("info")
				.category("info-category")
				.deploy();


		System.out.println("部署成功：流程部署id"+deployment.getId());
		System.out.println("deployment.getName()="+deployment.getName());
		System.out.println("deployment.getCategory()="+deployment.getCategory());
		System.out.println("deployment.getTenantId()="+deployment.getTenantId());
		System.out.println("deployment.DeploymentTime()="+deployment.getDeploymentTime());
		System.out.println(deployment.getId());
		return true;
	}


	@GetMapping("/startProcess")
	public boolean startProcess(@RequestParam("key") String key) {
		//根据id启动
		//runtimeService.startProcessInstanceById("HelloWord:1:4");
		//也可以根据key启动
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(key);
		System.out.println("processInstance.getId() = " + processInstance.getId());
		System.out.println(processInstance.getProcessDefinitionId());
		System.out.println("流程启动成功!!");
		return  true;
	}

}
