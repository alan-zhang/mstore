<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/admin.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/roles-admin.css">
<%@ include file="../shared/common.jsp" %>

</head>
<body>
<%@ include file="../shared/header.jsp"%>

<%@ include file="../templates/roles-template.html"%>  
<script src="<%=request.getContextPath()%>/scripts/require.js" defer async data-main="scripts/admin-roles-loader"></script>
<!-- 
<script src="<%=request.getContextPath()%>/scripts/libs/jquery-1.10.2.js"></script>
<script src="<%=request.getContextPath()%>/scripts/libs/handlebars-1.1.2.js"></script>
<script src="<%=request.getContextPath()%>/scripts/libs/ember-1.2.0.js"></script>
<script src="<%=request.getContextPath()%>/scripts/libs/ember-data.js"></script>
<script src="<%=request.getContextPath()%>/scripts/libs/ember-localstorge-adapter.js"></script>
<script src="<%=request.getContextPath()%>/scripts/admin/roles/app.js"></script>
-->
</body>
</html>