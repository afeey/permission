<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>角色列表</title>
	
	<link rel="stylesheet" href="${rc.contextPath}/js/bootstrap/css/bootstrap.min.css" />

	<style type="text/css">
		.container
		{
			width:280px;
			margin-top:100px;
		}
		.error
		{
			color:red;
			padding:10px 0;
		}
	</style>
</head>
<body>
	<div role="main" class="container">
		<form method="post">
			<div id="msg" class="error"></div>
			<div class="form-group">
				<label for="username">用户名</label><input type="text" class="form-control" name="username" id="username" placeholder="用户名" />
			</div>
			<div class="form-group">
				<label for="password">密码</label><input type="password" class="form-control" name="password" id="password" placeholder="密码" />
			</div>
			<div class="form-group">
				<button type="button" id="login" class="btn btn-success">登录</button>
			</div>
		</form>
	</div>
	
	<!-- jquery -->
	<script src="${rc.contextPath}/js/jquery.min.js"></script>
	<!-- bootstrap -->
	<script type="text/javascript" src="${rc.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function(){
  		$('#login').click(login);
  		$("input[type='password']").keypress(function(e){
  			if (event.keyCode == 13) {
    			login()();
  			}
		})
	});
	
	function login(){
		var vo={};
		vo.username=$.trim($("#username").val());
    	vo.password=$.trim($("#password").val());
    	
    	if(vo.username.length==0){
    		$('#msg').html("用户名不能为空");
    		return;
    	}
    	if(vo.password.length==0){
    		$('#msg').html("密码不能为空");
    		return;
    	}
    	
    	$('#login').val("登录中...");
    	$.ajax({
    		type: "post",
    		url: "${rc.contextPath}/api/login",
    		data: vo,
    		dataType: "json",
     		success: function(result){
     			$('#login').val("登录");
     			if(result.success){
     				location.href='${rc.contextPath}/';
     			}else{
     				$('#msg').html(result.message);
     			}
    		}
    	});
	}
	
	</script>
</body>
</html>