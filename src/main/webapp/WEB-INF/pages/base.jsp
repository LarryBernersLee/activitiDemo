<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
    var href = location.href;
    var protocol = location.protocol;
    var host = location.host;
    var contextPath = '<%=request.getContextPath()%>';
    var baseHref = '<base href="' + protocol + '//' + host + contextPath + '/' + '">';
    var basePath = protocol + '//' + host + contextPath;
    console.log("basePath==" + basePath);
</script>

<html>
<head>

	<link href="${ctx}/css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">

	<link href="css/sweetalert.css" rel="stylesheet">
	
	<style type="text/css">  
		<!-- 浏览器自带滚动条样式 -->
		::-webkit-scrollbar-track {background-color: #F5F5F5;}
		::-webkit-scrollbar{width:7px; height:7px; background-color:#FFFFFF}
		::-webkit-scrollbar-thumb{background-color:#CCCCCC; border-radius:8px;}
		
		<!-- input-placeholder 样式  -->
 		input::-webkit-input-placeholder {} 
		input::-webkit-input-placeholder{color:#aaaaaa; font-size:10px;}
		
	</style> 

</head>

</html>

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js?v=3.3.7"></script>


<script type="text/javascript" src="js/sweetalert.min.js"></script>

<script type="text/javascript">
//异常处理方法
// function handlException(fileName, urlName, data) {
// 	console.error("There are some exceptions...");
// 	console.log("file = " + fileName + "		url = " + urlName);
// 	console.log(data);

// 	var code = data.status;
// 	var content = data.responseText;
	
// 	var typeStr = "";
// 	if(code == 200)
// 		typeStr = "系统异常";
// 	else
// 		typeStr = "服务器开小差了";
		
// 	swal(typeStr, code + "\r\n" + content, "error");
// }
	
</script>
