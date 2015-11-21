<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>当前会话</title>
	
	<link rel="stylesheet" href="${rc.contextPath}/js/bootstrap/css/bootstrap.min.css" />
	<style type="text/css">
	#msg
	{
		color:red;
		padding:10px 0;
	}
</style>
</head>
<body>
	<ol class="breadcrumb">
  		<li><a href="#">会话管理</a></li>
  		<li class="active">当前会话</li>
	</ol>
	<div role="main" class="container-fluid">
		<div id="msg"></div>
      	<div class="row">
			 <div class="col-sm-4 text-right">会话ID :</div>
			 <div class="col-sm-8" id="id"></div>
		</div>
		<div class="row">
			 <div class="col-sm-4 text-right">IP :</div>
			 <div class="col-sm-8" id="host"></div>
		</div>
		<div class="row">
			 <div class="col-sm-4 text-right">超时设定(ms) :</div>
			 <div class="col-sm-8" id="timeout"></div>
		</div>
		<div class="row">
			 <div class="col-sm-4 text-right">登录时间 :</div>
			 <div class="col-sm-8" id="startTimestamp"></div>
		</div>
		<div class="row">
			 <div class="col-sm-4 text-right">最后访问 :</div>
			 <div class="col-sm-8" id="lastAccessTime"></div>
		</div>
	</div>
	
	<!-- jquery -->
	<script src="${rc.contextPath}/js/jquery.min.js"></script>
	<!-- bootstrap -->
	<script type="text/javascript" src="${rc.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	<!-- system -->
	<script type="text/javascript" src="${rc.contextPath}/js/system/common.js"></script>

	<script type="text/javascript">
	$(document).ready(function(){
		query();
	});
	
	function query(){
    	$('#msg').html("数据加载中...");
    	$.ajax({
    		type: "post",
    		url: "${rc.contextPath}/api/session/current",
    		data: "",
    		dataType: "json",
     		success: function(result){
     			if(!result.success){
					$('#msg').html(result.message);
					retrun;
				}
				$('#msg').html("");
				if(result.data!=null){
					var session=result.data;
					$("#id").html(session.id);
     				$("#host").html(session.host);
     				$("#timeout").html(session.timeout);
     				$("#startTimestamp").html(new Date(session.startTimestamp).Format('yyyy-MM-dd hh:mm:ss'));
    				$("#lastAccessTime").html(new Date(session.lastAccessTime).Format('yyyy-MM-dd hh:mm:ss'));
				}
    		}
    	});
	}
	</script>
</body>
</html>