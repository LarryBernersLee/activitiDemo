package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;
 

public class BpmDefinitionTest extends BaseTestCase {
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	TaskService taskService;

	//s@Test
	public void testDeploy() throws IOException {
		InputStream is = readXmlFile();
		Assert.assertNotNull(is);
		// 发布流程
		Deployment deployment = repositoryService.createDeployment().addInputStream("bpmn20.xml", is)
				.name("holidayRequest1").deploy();
		Assert.assertNotNull(deployment);
		System.out.println("deployId:" + deployment.getId());
		// 查询流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).singleResult();

		Long businessKey = new Double(1000000 * Math.random()).longValue();
		// 启动流程
		System.out.println("启动流程ID：" + processDefinition.getId());
		System.out.println("businessKey：" + businessKey.toString());

		/*
		 * runtimeService.startProcessInstanceById(processDefinition.getId(),
		 * businessKey.toString());
		 * 
		 * //查询任务实例 List<Task>
		 * taskList=taskService.createTaskQuery().processDefinitionId(
		 * processDefinition.getId()).list();
		 * Assert.assertNotNull(taskList==null);
		 * Assert.assertTrue(taskList.size()>0); for(Task task:taskList){
		 * System.out.println("task name is " + task.getName() +
		 * " ,task key is " + task.getTaskDefinitionKey()); }
		 */

	}

	public InputStream readXmlFile() throws IOException {
		String filePath = "leaveBill2.bpmn";
		return Class.class.getClass().getResource("/" + filePath).openStream();
	}

	// @Test
	public void findMyPersonalTask() {
		String assignee = "王五";
		List<Task> list = taskService.createTaskQuery()// 创建任务查询对象
				/** 查询条件（where部分） */
				.taskAssignee(assignee)// 指定个人任务查询，指定办理人
				// .taskCandidateUser(candidateUser)//组任务的办理人查询
				// .processDefinitionId(processDefinitionId)//使用流程定义ID查询
				// .processInstanceId(processInstanceId)//使用流程实例ID查询
				// .executionId(executionId)//使用执行对象ID查询
				/** 排序 */
				.orderByTaskCreateTime().asc()// 使用创建时间的升序排列
				/** 返回结果集 */
				// .singleResult()//返回惟一结果集
				// .count()//返回结果集的数量
				// .listPage(firstResult, maxResults);//分页查询
				.list();// 返回列表
		if (list != null && list.size() > 0) {
			for (Task task : list) {
				System.out.println("任务ID:" + task.getId());
				System.out.println("任务名称:" + task.getName());
				System.out.println("任务的创建时间:" + task.getCreateTime());
				System.out.println("任务的办理人:" + task.getAssignee());
				System.out.println("流程实例ID：" + task.getProcessInstanceId());
				System.out.println("执行对象ID:" + task.getExecutionId());
				System.out.println("流程定义ID:" + task.getProcessDefinitionId());
				System.out.println("########################################################");
			}
		}
	}

	
	@Test
	public void start() {
		Long businessKey = new Double(1000000 * Math.random()).longValue();
		System.out.println("businessKey:"+businessKey);
		//ProcessInstance s=runtimeService.startProcessInstanceById("myProcess:2:2504", businessKey.toString());
		
		Map<String, Object> variables = new HashMap<String,Object>();
		variables.put("inputUser", "zhangchangyong");//表示惟一用户
		runtimeService.startProcessInstanceById("myProcess:2:2504", variables);
 
		// 查询任务实例
		List<Task> taskList = taskService.createTaskQuery().processDefinitionId("myProcess:2:2504").list();
		Assert.assertNotNull(taskList == null);
		Assert.assertTrue(taskList.size() > 0);
		for (Task task : taskList) {
			System.out.println("task name is " + task.getName() + " ,task key is " + task.getTaskDefinitionKey());
		}
		
		long count = runtimeService.createProcessInstanceQuery().count();  
		System.out.println("流程实例数量：" + count);  
	}

	
	
	
	public void assign(){
		
		
	}
	
	 /** 
     * 学习完成指定的任务 
     */  
  
	//@Test
    public void testCompleteTask() {  
        String taskId = "10002";  
    	Map<String, Object> variables = new HashMap<String,Object>();
 
		//3：使用任务ID，完成当前人的个人任务，同时流程变量
		taskService.complete(taskId, variables);
 
        System.out.println("任务ID：" + taskId + "执行完成");  
    }  
    
    
    public void testAssi(){
 
    }
    
 
}
