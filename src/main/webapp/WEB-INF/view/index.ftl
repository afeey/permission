<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>首页</title>
	
	<link rel="stylesheet" href="${rc.contextPath}/js/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
    	<div class="navbar-header">
      		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#system_navbar" aria-expanded="false">
     			<span class="sr-only">Toggle navigation</span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
      		</button>
      		<a class="navbar-brand" href="#">LOGO</a>
    	</div>
    	<div class="collapse navbar-collapse" id="system_navbar">
      		<ul class="nav navbar-nav">
      			<li class="active"><a href="${rc.contextPath}">首页</a></li>
      			<li><a href="#">文档</a></li>
        		<li class="dropdown">
        			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
        				系统管理<span class="caret"></span>
        			</a>
         			<ul class="dropdown-menu">
            			<li><a href="${rc.contextPath}/admin/resource/list">资源列表</a></li>
            			<li><a href="${rc.contextPath}/admin/role/list">角色列表</a></li>
            			<li><a href="${rc.contextPath}/admin/user/list">用户列表</a></li>
            			<li role="separator" class="divider"></li>
            			<li><a href="${rc.contextPath}/admin/session/list">会话列表</a></li>
            			<li><a href="${rc.contextPath}/admin/session/current">当前会话</a></li>
          			</ul>
      			</li>
      		</ul>
      		<form class="navbar-form navbar-left" role="search">
        		<div class="form-group">
          			<input type="text" class="form-control" placeholder="内容">
        		</div>
        		<button type="submit" class="btn btn-default">搜索</button>
      		</form>
      		<ul class="nav navbar-nav navbar-right">
      		<#if username!=''>
      			<li class="dropdown">
          			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          				${username}<span class="caret"></span>
          			</a>
          			<ul class="dropdown-menu">
            			<li><a href="#">我的账号</a></li>
            			<li><a href="#">修改密码</a></li>
            			<li role="separator" class="divider"></li>
            			<li><a href="${rc.contextPath}/logout">退出</a></li>
          			</ul>
        		</li>
      		<#else>
      	 		<li><a href="${rc.contextPath}/login">登录</a></li>
      		</#if>
      		</ul>
    	</div>
	</div>
</nav>

	<!-- jquery -->
	<script src="${rc.contextPath}/js/jquery.min.js"></script>
	<!-- bootstrap -->
	<script type="text/javascript" src="${rc.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>