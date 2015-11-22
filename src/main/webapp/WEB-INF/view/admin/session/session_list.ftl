<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>会话列表</title>
	
	<link rel="stylesheet" href="${rc.contextPath}/js/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${rc.contextPath}/js/datatables/css/dataTables.bootstrap.min.css" />
	<link rel="stylesheet" href="${rc.contextPath}/css/theme.min.css" />
</head>
<body>
	<ol class="breadcrumb">
  		<li><a href="#">会话管理</a></li>
  		<li class="active">会话列表</li>
	</ol>
	<div role="main" class="container-fluid">
		<div role="action">
			<form class="form-inline" id="frm_action">
				<div class="form-group">
    				<label for="f_username">用户名</label>
    				<input type="text" class="form-control" id="f_username" name="f_username"/>
  				</div>
  				<button type="button" id="btn_query" class="btn btn-success">查询</button>
			</form>
		</div>
      	<table id="list" class="table table-bordered table-condensed table-hover" width="100%">
        	<thead>
          		<tr>
            		<th>会话ID</th>
					<th>用户ID</th>
					<th>用户名</th>
					<th>IP</th>
					<th>超时设定(ms)</th>
					<th>登录时间</th>
					<th>最后访问</th>
          		</tr>
        	</thead>
      	</table>
	</div>
	
	<!-- jquery -->
	<script type="text/javascript" src="${rc.contextPath}/js/jquery.min.js"></script>
	<!-- bootstrap -->
	<script type="text/javascript" src="${rc.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	<!-- datatables -->
	<script type="text/javascript" src="${rc.contextPath}/js/datatables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/js/datatables/js/dataTables.bootstrap.min.js"></script>
	<!-- system -->
	<script type="text/javascript" src="${rc.contextPath}/js/system/common.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//绑定事件
  			$('#btn_query').click(query);

  			//初始化
  			initTable();
		});
		
		var table; //表格
		//初始化表格
		function initTable(){
			table =
			$('#list').DataTable({
        		serverSide: true,
        		ajax: function (data, callback, settings) {
        			data.userName = $.trim($("#f_username").val());
        			$.ajax({
        				type: "post",
        				url: "${rc.contextPath}/api/session/list",
        				data: data,
        				dataType: "json",
        				success:function(result){
        					if(!result.success){
        						alert(result.message);
        						sessionExpired('${rc.contextPath}',result);
        					}
        					callback(result);
        				}
        			});
        		},
        		columns: [
                    { data: "id" },
                    { data: "userid" },
                    { data: "username" },
                    { data: "host" },
                    { data: "timeout" },
                    { data: "startTimestamp" },
                    { data: "lastAccessTime" }
                ],
                initComplete: function (setting, json) {
                   $('#list_length').insertBefore($('#list_info'));
                   $('#list_wrapper div:first').remove();
                },
                lengthMenu: [[10,15,20], ["10","15","20"]],
                processing: true,
                searching: false,
    			ordering:  false,
                pagingType: "full_numbers",
                language: {
                	lengthMenu: "_MENU_条每页&nbsp;&nbsp;",
                	processing: "数据查询中...",
            		zeroRecords: "未查询到记录",
            		info: "当前 _PAGE_/_PAGES_ 页  共 _MAX_ 条记录",
            		infoFiltered: "(从 _MAX_条记录中过滤)",
            		infoEmpty: "0条记录",
            		paginate: {
                        previous: "上一页",
                     	next: "下一页",
                     	first: "首页",
                     	last: "尾页"
                    }
                }
    		});
    	}
    	
    	//查询
		function query(){
			table.ajax.reload();
		}
	</script>
</body>
</html>