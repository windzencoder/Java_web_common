<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ basePath;
%>
<html>
<body>
	<h2>kaptcha</h2>

	<img alt="random" src="randomcode.jpg" onclick="changeR(this)" style="cursor: pointer;">
	<form action="check.jsp">
		<input type="text" name="r"> <input type="submit" value="s">
	</form>
	
	
	<script type="text/javascript">
		function changeR(node){
			// 用于点击时产生不同的验证码
			node.src = "randomcode.jpg?time="+new Date().getTime() ;	
		}
</script>
	
</body>
</html>
