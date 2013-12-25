<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<%@ include file="../shared/common.jsp" %>
</head>
<body>
<%@ include file="../shared/header.jsp"%>

<div class="ll-auth">
    <input value="" id="name" placeholder="Authority name" />
    <input value="" id="desc" placeholder="Authority description" />
    <button id="save">Save</button>
</div>

<div class="ll-auth-show">
    <ul id="auths"></ul>
</div>

<script id="authList" type="text/html">
    <li>
        <div>
        <span>${authorityName}</span><span>${authorityDesc}</span><span>${createTime}</span>
        </div>
        <div>
            <a class="del" href="javascript:void(0);" name="${id}">Delete</a>
        </div>
    </li>
</script>
<script src="<%=request.getContextPath()%>/scripts/require.js" data-main="scripts/admin-auth-loader"></script>
</body>
</html>