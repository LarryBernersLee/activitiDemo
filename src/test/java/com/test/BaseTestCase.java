package com.test;
 

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-activiti-test.xml"})
public class BaseTestCase{

    @Test
    public void test(){
    	System.out.println ("test"); 
    }
    
}