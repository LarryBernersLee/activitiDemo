package com.test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class FlowTest extends BaseTestCase{
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	TaskService taskService;
	@Resource
	private ProcessEngine processEngine;
	
	@Resource
	private HistoryService historyService;
	
	public List<FlowNode> selectAllWorkflow(String flowkey) {
		List<ProcessDefinition> list = processEngine.getRepositoryService() // 获取service类  
		.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc() // 创建流程定义查询  
		.processDefinitionKey(flowkey) // 通过key查询  
		.list();
		Map<String,ProcessDefinition> map = new LinkedHashMap<String,ProcessDefinition>();
		if(list.size() > 0 && null != list) {
			for(ProcessDefinition pd:list){
				map.put(pd.getKey(), pd);
			}
		}
		BpmnModel bpmnModel = repositoryService.getBpmnModel(map.get(flowkey).getId());
		org.activiti.bpmn.model.Process bpmnProcess = bpmnModel.getMainProcess();
		List<FlowNode> listFlowNodes = bpmnProcess.findFlowElementsOfType(FlowNode.class);
		return listFlowNodes;
	}
	
	@Test
	public void testFlow() {

		
		String procinstId="16BD4695-DA97-468F-A144-43A93271F5D9";

 
/*		HistoricProcessInstance history = historyService.createHistoricProcessInstanceQuery()
				 .processInstanceBusinessKey(procinstId)
				 .singleResult();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(history.getProcessDefinitionId());
		org.activiti.bpmn.model.Process bpmnProcess = bpmnModel.getMainProcess();
		List<FlowNode> listFlowNodes = bpmnProcess.findFlowElementsOfType(FlowNode.class);
		for(FlowNode fn:listFlowNodes) {
			
			System.out.println("fn:"+fn.getName());
		}
		
 
      String pProcessInstanceId = history.getId();
 
      List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
              .processInstanceId(pProcessInstanceId).orderByHistoricActivityInstanceId().desc().list();
      String nodename = null;
      if(historicActivityInstanceList != null) {
   	   	nodename = historicActivityInstanceList.get(0).getActivityName();
      }
      
      System.out.println("nodeName:"+nodename);
      
      
      for(FlowNode fn:listFlowNodes) {
    	  String name =fn.getName();
    	  if(!name.equals(nodename)) {
    		  
    		  System.out.println("111111111");
    	  }else {
    		  
    		  System.out.println("222222222");
    	  }
		 
      }
*/
      String name=getTaskName(procinstId);
      System.out.println("==================name:"+name);
 
	}
	
	
	public String getTaskName(String procinstId) {
		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(procinstId)
				.list();
		if (list.size() > 0 && list != null) {
			ProcessInstance pi = list.get(0);
			String processInstanceid = pi.getProcessInstanceId();
			Task task = taskService.createTaskQuery().processInstanceId(processInstanceid).singleResult();
			String taskName = task.getName();
			return taskName;
		}
		return "";
	}
	
}
