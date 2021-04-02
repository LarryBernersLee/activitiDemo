package com.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.toolkit.StringUtils;

import net.sf.json.JSONObject;

public class TokenVerificationInterceptor  implements HandlerInterceptor {
	public String[] interceptUrls;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		System.out.println("requestUrl1:"+requestUrl);
		//requestUrl = requestUrl.substring(requestUrl.indexOf("/"),requestUrl.lastIndexOf("/"));
		response.setContentType("text/html; charset=utf-8");
         if (null != interceptUrls && interceptUrls.length >= 1)  
            for (String url : interceptUrls) {  
            	System.out.println("request url:"+requestUrl+" url:"+url);
                if (requestUrl.equals(url)) {
                	String resourceId=request.getParameter("resourceId");
                	System.out.println("resourceId:"+resourceId);
                	if(StringUtils.isEmpty(resourceId)) {
                		printMsg(response);
                		return false;
                	}else {
 
                		printMsg(response);
                		return false;
                	}
                }
            }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
 
	public void setInterceptUrls(String[] interceptUrls) {
		this.interceptUrls = interceptUrls;
	}

	
	public void printMsg(HttpServletResponse response) throws IOException {
    	PrintWriter out = response.getWriter();
		Map<String,String> map=new HashMap<String,String>();
		map.put("ret", "400");
		map.put("msg", "没有访问权限!");
		JSONObject  jsonObject = JSONObject.fromObject(map);
		out.print(jsonObject);
		out.close();
	}
}