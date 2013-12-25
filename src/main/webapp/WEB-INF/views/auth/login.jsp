<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<%@ include file="../shared/base-href.jsp"%>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/comm.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/login.css">
<script src="<%=request.getContextPath()%>/scripts/require.js" defer async="true" data-main="scripts/auth-loader"></script>
</head>
<body class="lg-body">
	<div class="lg-panel">
		<span>用户登录</span>
		<div class="lg-div">
			<div class="lg-panel-row">
				<input class="lg-panel-textbox lg-user" id="userName" name="j_username"
					type="text" placeholder="用户名">
			</div>
			<div class="lg-panel-row">
				<input class="lg-panel-textbox lg-psw" id="password" name="j_password"
					type="password" placeholder="密码">
			</div>
			<div class="lg-error"></div>
			<div class="lg-panel-row">
				<input class="lg-panel-button" id="login" type="submit" value="登录" />
				<label><a class="lg-forget" href="#">忘记密码?</a></label>
			</div>
			
		</div>
	</div>
	<p class="copyright">© 2013 - 2014 Copyright by Alan. all rights reserved.</p>
</body>
</html>