<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<header class="header">
	<a href="auth/logout">退出</a>
</header>

<nav class="ll-nav-p">
	<ul class="ll-nav">
		<!--<li><a href="#ll-bkm">后台管理</a>
			  <div id="ll-bkm" class="ll-dm">
				<ul>
					<li><a href="#">用户管理</a></li>
					<li><a href="#">角色管理</a></li>
					<li><a href="#">权限管理</a></li>
					<li><a href="#">资源管理</a></li>
				</ul>
			</div>
		</li>-->
		<li><a href="admin/resources-manage">资源管理</a></li>		
		<li><a href="admin/roles-manage">角色管理</a></li>
		<li><a href="admin/authorities-manage">权限管理</a></li>
		<li><a href="admin/user-manage">用户管理</a></li>
	</ul>
</nav>

<div id="alert" class="ll-alert alert">
<button type="button" class="close">&times;</button>
<div id="message"></div>
</div>