<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String basePath=request.getContextPath(); 
	String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + basePath;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<base href="<%=path%>/">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>

<script type="text/javascript" >
	$(function(){
		$('.thumbs a').click(function(){
			var largePath = $(this).attr('href');
			var largeAlt = $(this).attr('alt');
			$('#largeImg').attr({
				src:largePath,
				alt:largeAlt
			});
			//阻止a标签默认的事件
			return false;
		});
		
		$('#myfile').change(function(){
			var input = this;
			//预览图片
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        reader.onload = function (e) {
		        		var src = e.target.result;
					$('#previewImg').attr('src', src);
		        }
		        reader.readAsDataURL(input.files[0]);
		    }
		});
		//展示大图
		var large = $('#large');
		large.hide();
		$('#previewImg').mousemove(function(e){
			large.css({
				top:e.pageY,
				left:e.pageX
			}).html('<img src = "' + this.src + '" />').show();
		}).mouseout(function(){
			large.hide();
		});
		
	});


	
</script>

</head>
<body>

<!-- 预览图片 -->
<img id="previewImg" src="images/preview.jpg" style="widith:80px;height:80px;"></img>
<form action="uploadServlet.do" method="post" enctype="multipart/form-data">
	请选择图片:<input id="myfile" name="myfile" type="file"  />
	<input type="submit" value="提交"/>${result}
</form>
<div id="large"></div>
<hr>
<a href="downloadServlet.do?filename=demo.txt">demo.txt</a>${errorResult}
<hr>

<h2>图片预览</h2>
<p><img id="largeImg" src="images/img1-lg.jpg" alt="large-image"/></p>
<p class="thumbs">
	<a href="images/img2-lg.jpg" title="image2"><img alt="" src="images/img2-thumb.jpg"></a>
	<a href="images/img3-lg.jpg" title="image3"><img alt="" src="images/img3-thumb.jpg"></a>
	<a href="images/img4-lg.jpg" title="image4"><img alt="" src="images/img4-thumb.jpg"></a>
	<a href="images/img5-lg.jpg" title="image5"><img alt="" src="images/img5-thumb.jpg"></a>
	<a href="images/img6-lg.jpg" title="image6"><img alt="" src="images/img6-thumb.jpg"></a>
</p>
</body>
</html>




