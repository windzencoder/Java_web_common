<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath=request.getContextPath(); 
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + basePath;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/servlet/LoginServlet" method="get">
		验证码：<input type="text" name="checkcode" >
		<img id="imagecode" alt="验证码" src="<%=request.getContextPath() %>/servlet/ImageServlet">
		<a href="javascript:reloadCode()">看不清楚</a>
		<input type="submit" value="提交">
	</form>

	
	
	
	<script type="text/javascript">
	
		function reloadCode(){
			//有缓存，所以添加上time
			var time =  new Date().getTime();
			document.getElementById("imagecode").src="<%=request.getContextPath() %>/servlet/ImageServlet?d="+time;
		}
	
	</script>
	
</body>
</html>