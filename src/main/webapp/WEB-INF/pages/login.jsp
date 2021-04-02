<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>登录</title>
	
	<%@include file="base.jsp" %>
</head>

<body>
	<div class="login">
		<div>
			<input id="username" class="user-pwd" type="text" placeholder="用户名" style="border-bottom:none;"/>
		</div>
		<div>
			<input id="password" class="user-pwd" type="password" placeholder="密码" />
		</div>
		<div>
			<button id="loginSys">登录系统</button>
		</div>
	</div>
</body>

<footer>
</footer>

</html>

<script type="text/javascript">
	$("#loginSys").click(function() {
		document.location.href = "navbar/navbarList";
	});

</script>