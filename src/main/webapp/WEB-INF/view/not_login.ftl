<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>未登录</title>
	<link rel="stylesheet" href="${rc.contextPath}/js/bootstrap/css/bootstrap.min.css" />
	
	<style type="text/css">
		.panel-default{
			margin:100px auto;
			padding:10px 20px;
			width:600px;
			text-align:center;
		}
		
	</style>
</head>
<body>
	<div role="main" class="container-fluid">
		<div class="panel panel-default">
  			<div class="panel-body">
				<a href="javascript:void(0);" id="login">您尚未登录，点击登录。
				<span id="second"></span>秒后自动跳转</a>
			</div>
		</div>
	</div>
	<!-- jquery -->
	<script type="text/javascript" src="${rc.contextPath}/js/jquery.min.js"></script>
	<!-- bootstrap -->
	<script type="text/javascript" src="${rc.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function(){
			
		$("#login").click(gotoLogin)
		
		//倒计时跳转
		var seconds=10;
		$("#second").html(seconds);
		jump(seconds);
	});
	
	//倒计时执行跳转
	function jump(seconds) { 
    	setTimeout(function(){ 
        	seconds--;
        	$('#second').html(seconds);
       		if(seconds > 0) {
                jump(seconds); 
            } else { 
               gotoLogin();    
            } 
        }, 1000); 
	}
    
	function gotoLogin(){
		top.location.href="${rc.contextPath}/login";
	}
	</script>
</body>
</html>