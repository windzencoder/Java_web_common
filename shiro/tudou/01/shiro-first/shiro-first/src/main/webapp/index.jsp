<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% 
	String basePath=request.getContextPath(); 
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + basePath;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Hello  <shiro:principal></shiro:principal></h1>

<shiro:guest>
<a href="<%=path %>/login">登录</a><br/>
</shiro:guest>

<shiro:user>
<shiro:hasPermission name="user:add">
<a href="<%=path %>/user/add.jsp">用户添加</a><br/>
</shiro:hasPermission>
<shiro:hasRole name="admin">
<a href="<%=path %>/admin/">管理员页面</a><br/>
</shiro:hasRole>
<a href="<%=path %>/user/list.jsp">用户列表</a><br/>


<a href="<%=path %>/logout">退出登录</a><br/>
</shiro:user>


</body>
</html>