<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户登录</title>

<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style type="text/css">
	.container
	{
		width:280px;
		margin-top:100px;
	}
	#msg
	{
		color:red;
		padding:10px 0;
	}
</style>

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
		var user={};
		user.username=$.trim($("#username").val());
    	user.password=$.trim($("#password").val());
    	
    	if(user.username.length==0){
    		$('#msg').html("用户名不能为空");
    		return;
    	}
    	if(user.password.length==0){
    		$('#msg').html("密码不能为空");
    		return;
    	}
    	
    	$('#login').val("登录中...");
    	$.ajax({
    		type: "post",
    		url: "http:/192.168.1.119/api/api/login",
    		data: user,
    		dataType: "json",
     		success: function(data){
     			$('#login').val("登录");
     			if(data.success){
     				location.href='${rc.contextPath}/';
     			}else{
     				$('#msg').html(data.msg);
     			}
    		}
    	});
	}
	
</script>
</head>
<body>
	<div class="container">
	<form method="post">
		<div id="msg"></div>
		<div class="form-group">
			<label for="username">用户名</label><input type="text" class="form-control" name="username" id="username" placeholder="用户名" />
		</div>
		<div class="form-group">
			<label for="password">密码</label><input type="password" class="form-control" name="password" id="password" placeholder="密码" />
		</div>
		<div class="form-group">
			<input type="button" id="login" class="btn btn-success" value="登录"/>
		</div>
	</form>
	</div>
</body>
</html>