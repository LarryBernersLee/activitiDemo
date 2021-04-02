package com.aoyuntech.yunling.activiti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/resource")
public class TestController {
	
	@RequestMapping(value="/getResourceDetails")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView view= new ModelAndView("logout");
		System.out.println("========int========");
		String resourceId=request.getParameter("resourceId");
    	System.out.println("resourceId:"+resourceId);
		System.out.println("========out========");
		return view;
	}


	

}
