package com.test;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;

import javax.annotation.Resource;

public class TestAct {
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    TaskService taskService;
    @Test
    public void isEmpty() {
        if (repositoryService != null)
            System.out.println("repositoryService 获取成功");
        else
            System.out.println("repositoryService 获取失败");

        if (runtimeService != null)
            System.out.println("runtimeService 获取成功");
        else
            System.out.println("runtimeService 获取失败");

        if (taskService != null)
            System.out.println("taskService 获取成功");
        else
            System.out.println("taskService 获取失败");
    }
}
