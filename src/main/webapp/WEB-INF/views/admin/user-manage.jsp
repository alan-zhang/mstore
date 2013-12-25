<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户管理</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/admin.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/pagination.css">
    <%@ include file="../shared/common.jsp" %>
    <script src="<%=request.getContextPath()%>/scripts/require.js" defer async
            data-main="scripts/admin-users-loader"></script>
</head>
<body>
<%@ include file="../shared/header.jsp" %>
<div class="ll-body">
    <div class="ll-content">
        <div class="ll-data"></div>
        <div class="ll-pg"></div>
    </div>
    <div class="ll-edit-panel"></div>
</div>
<%@ include file="../templates/admin-template.html" %>
</body>
</html>