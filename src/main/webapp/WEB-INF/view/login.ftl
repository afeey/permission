<!DOCTYPE html>
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
	.container{
		width:280px;
		margin-top:100px;
	}
</style>
</head>
<body>
	<div class="container">
	<form action="${rc.contextPath}/login" method="post">
		<div>${error}</div>
		<div class="form-group">
			<label for="username">用户名</label><input type="text" class="form-control" name="username" id="username" placeholder="用户名" />
		</div>
		<div class="form-group">
			<label for="password">密码</label><input type="password" class="form-control" name="password" id="password" placeholder="密码" />
		</div>
		<div class="form-group">
			<input type="submit" class="btn btn-success" value="登录"/>
		</div>
	</form>
	</div>
</body>
</html>