<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath=request.getContextPath(); 
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + basePath;
%>
<html>
<body>
<h2>Simple Captcha Servlet sample</h2>

<form action="submit.action" method="post">
     <img src="jcaptcha.jpg" /> <input type="text" name="japtcha" value="" />
     <input type="submit"/>
</form>
</body>
</html>
