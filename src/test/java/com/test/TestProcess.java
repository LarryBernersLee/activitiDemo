package com.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.annotations.TableField;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Assert;
import org.junit.Test;

public class TestProcess extends BaseTestCase {
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    TaskService taskService;


    @Test
    public void start() {
        System.out.println("start the process");
        if (repositoryService != null) {
            System.out.println(repositoryService);
        }
    }


    @Test
    public void deploy() throws IOException {
        System.out.println("start deploy process");
        // String path = "F:\\code\\ActivitiSpring\\src\\main\\resources\\group_process.bpmn";
        String resPath = "variable.bpmn";
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(resPath)
                .name("deploy")
                .category("category")
                .deploy();

        System.out.println(deployment.getId());
        System.out.println(deployment.getKey());
        System.out.println("deploy success");
    }


    // 流程定义 --> 创建流程实例
    @Test
    public void startProcess() {
        System.out.println("start create process instance");
        String defId = "variableProcess:2:70004";
        //Map<String, Object> variable = getVariable("user1");
        Map<String, Object> variable = new HashMap<>();
        variable.put("assign", "user1");
//        variable.put("user", new User("li ", 29));
//        variable.put("addr", "Beijing");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(defId, variable);
        System.out.println("processInstance.getId() = " + processInstance.getId());
        System.out.println(processInstance.getDeploymentId());
        System.out.println("processInstance.getProcessDefinitionId() = " + processInstance.getProcessDefinitionId());
    }

    private Map<String, Object> getVariable(String user) {
        Map<String, Object> variable = new HashMap<>();
        variable.put("assign", user);
        return variable;
    }


    @Test
    public void changeVariable() {
        String executionId = "60001";
        Map<String, Object> variable = new HashMap<>();
        variable.put("description", "I need a rest");
        runtimeService.setVariables(executionId, variable);
    }

    @Test
    public void getVariable() {
        System.out.println("获取变量");
        String executionId = "62501";
        Object info = runtimeService.getVariable(executionId, "user");
        System.out.println("============" + info.getClass() + "===========");
    }


    // 流程实例查询
    @Test
    public void processInstanceQuery() {
        System.out.println("============ query process instances ============");
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        List<ProcessInstance> processInstanceList = processInstanceQuery.list();
        processInstanceList.forEach(processInstance -> {
            System.out.println("processInstance.getId() = " + processInstance.getId());
            System.out.println("processInstance.getDeploymentId() = " + processInstance.getDeploymentId());
            System.out.println(processInstance.getProcessDefinitionId());
            System.out.println(processInstance.getProcessInstanceId());
        });
    }


    @Test
    public void executionQuery() {
        System.out.println("============ query process instance execution");
        ExecutionQuery executionQuery = runtimeService.createExecutionQuery();
        List<Execution> exceptionList = executionQuery.list();
        exceptionList.forEach(execution->{
            System.out.println("execution.getProcessInstanceId() = " + execution.getProcessInstanceId());
            System.out.println("execution.getName() = " + execution.getName());
        });
    }


    @Test
    public void taskQuery() {
        System.out.println("============ query all tasks ============");
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> taskList = taskQuery.list();
        taskList.forEach(task -> {
            System.out.println("name: " +task.getName());
            System.out.println("assign: " + task.getAssignee());
            System.out.println("processId: " + task.getProcessInstanceId());
            System.out.println("taskId: " + task.getId());
        });
    }


    @Test
    public void taskQueryByAssignee() {
        System.out.println("============ query task by assignee");
        TaskQuery taskQuery = taskService.createTaskQuery();
        String assignee = "user2";
        List<Task> taskList = taskQuery.taskAssignee(assignee).list();
        if (taskList == null || taskList.size() == 0) {
            System.out.println("not found tasks of " + assignee);
        } else {
            taskList.forEach(task -> {
                System.out.println("taskId: " + task.getId());
                System.out.println("assignee: " + task.getAssignee());
                System.out.println("name: " + task.getName());
            });
        }
    }


    @Test
    public void groupTaskQueryByTaskId() {
        String taskId = "20005";
        // 获得任务的candidate
        List<IdentityLink> linkList = taskService.getIdentityLinksForTask(taskId);
        linkList.forEach(link->{
            System.out.println("task: " + link.getTaskId());
            System.out.println("group: " + link.getGroupId());
            System.out.println("user: " + link.getUserId());
        });
    }

    @Test
    public void taskClaim() {
        String taskId = "20005";
        String userId = "user2";
        taskService.claim(taskId, userId);
        System.out.println("task claim success");
    }


    @Test
    public void taskRollBack() {
        String taskId = "20005";
        taskService.claim(taskId, null);
        System.out.println("task roll back success");
    }


    @Test
    public void deleteCrew() {
        System.out.println("============ delete crew ==========");
        String taskId = "20005";
        String userId = "user1";
        taskService.deleteCandidateUser(taskId, userId);
        System.out.println("delete crew success");
    }



    @Test
    public void completeTask() {
        System.out.println("============ complete task ============");
        TaskQuery taskQuery = taskService.createTaskQuery();
        String user = "user1";
        Task task = taskQuery.taskAssignee(user).singleResult();
        if (task == null) {
            System.out.println("do not get task" );
        } else {
            String taskId = task.getId();
            String assignee = task.getAssignee();
            System.out.println("taskId: " + taskId);
            System.out.println("assignee: " + assignee);
            //Map<String, Object> variable = getVariable("user66");
//            Map<String, Object> variable = new HashMap<>();
//            variable.put("money", 750);
            //taskService.complete(taskId, variable);
            taskService.complete(taskId);
            System.out.println("complete task: " + taskId);
        }
    }


}
