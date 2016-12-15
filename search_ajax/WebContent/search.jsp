<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath=request.getContextPath(); 
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + basePath;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajax搜索</title>

<style type="text/css">

	#mydiv {
		position: absolute;
		left: 50%;
		top:50%;
		margin-left: -200px;
		margin-top: -50px;
	}
	
	.mouseOver {
		background-color: #708090;
		color: #FFFAFA;
	}
	
	.mouseOut {
		background-color: #FFFFFF;
		color: #000000;
	}

</style>

<script type="text/javascript">
	
	var xmlHttp;
	function getMoreContent() {
		//获得用户的输入
		var content = document.getElementById("keyword");
		if(content.value == ""){
			//如果输入为空 清除内容
			clearContent();
			return;
		}
		//异步发送数据
		xmlHttp = createXMLHttp();
		//alert(xmlHttp);
		
		//给服务器发送数据
		var url = "search?keyword=" + encodeURI(encodeURI(content.value));
		xmlHttp.open("GET", url, true);
		xmlHttp.onreadystatechange = callback;
		xmlHttp.send();
	}
	
	//回调函数
	function callback() {
		
		xmlHttp.onreadystatechange = function() {
	        if (this.readyState == 4 && this.status == 200) {
	            var result = xmlHttp.responseText;
	            //解析获取的数据
	            var json = JSON.parse(result);
	            console.log(json);
	            //展示数据
	            setContent(json);
	       }
	    };
		
	}
	
	//设置内容
	function setContent(contents) {
		
		clearContent();
		//设置位置
		setLocation();
		var size = contents.length;
		for(var i=0; i<size; i++) {
			
			var nextNode = contents[i];
			
			var tr = document.createElement("tr");
			var td = document.createElement("td");
			td.setAttribute("border", 0);
			td.setAttribute("bgcolor", "#FFFFFF");
			td.onmouseover = function() {
				this.className = "mouseOver";
			}
			td.onmouseout = function() {
				this.className = "mouseOut";
			}
			//点击事件
			td.onclick = function() {
				alert("onclick");
			}
			
			td.onmousedown = function() {
				var text = this.innerText;
				document.getElementById("keyword").value = text;
			}
			
			//文本节点
			var text = document.createTextNode(nextNode);
			
			td.appendChild(text);
			tr.appendChild(td);
			document.getElementById("table_body").appendChild(tr);
			
		}
		
	}
	
	//清空数据
	function clearContent() {
		
		var tbody = document.getElementById("table_body");
		tbody.innerHTML = "";
		document.getElementById("popdiv").style.border = "none";
		
	}
	
	//当输入框失去焦点，清空信息
	function keywordBlur() {
		clearContent();
		
	}
	
	//创建XmlHttp对象
	function createXMLHttp() {
		var xhttp;
		if (window.XMLHttpRequest) {
		    xhttp = new XMLHttpRequest();
		} else {
		    xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		    if(!xhttp) {
		    		xhttp = new ActiveXObject("Msxml2.XMLHTTP");
		    }
		}
		return xhttp;
	}

	//设置位置
	function setLocation() {
		var input = document.getElementById("keyword");
		//输入框的宽度
		var width  = input.offsetWidth;
		//距离左边框的距离
		var left = input["offsetLeft"];
		var top = input["offsetTop"]+input.offsetHeight;
		//获取div
		var div = document.getElementById("popdiv");
		div.style.border = "black 1px solid";
		div.style.left = left + "px";
		div.style.top = top + "px";
		div.style.width = width + "px";
		document.getElementById("table").style.width = width + "px";
	}
	
</script>

</head>
<body>

	<div id="mydiv">
		<input type="text" size="50" id="keyword" onkeyup="getMoreContent()" 
			onblur="keywordBlur()" onfocus="getMoreContent()">
		<input type="button" value="百度一下" width="50px">
		
		<!-- 内容展示 -->
		<div id="popdiv">
			<table id="table" bgcolor="#fffafa" border="0" cellspacing="0" cellpadding="0">
				<tbody id="table_body">
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>