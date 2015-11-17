<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>角色列表</title>
	
	<!-- jquery -->
	<script type="text/javascript" src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	
	<!-- bootstrap -->
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" />
	<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
  			$('#btn_query').click(query);
  			$('#btn_add').click(showAdd);
		});
		
		function query(){
			alert('查询成功');
		}
		
		function showAdd(){
			$("#add_modal input[type='text'],#add_modal textarea").val("");
			$("#add_modal").modal('show');
		}
		
		function save(){
			alert('保存成功');
			$('#add_modal').modal('hide');
		}
	</script>

</head>
<body>
	<ol class="breadcrumb">
  		<li><a href="#">权限管理</a></li>
  		<li class="active">角色列表</li>
	</ol>
	<div role="main" class="container">
		<div role="action">
			<form class="form-inline" id="frm_action">
				<div class="form-group">
    				<label for="name">名称</label>
    				<input type="text" class="form-control" id="name" name="name" placeholder="名称"/>
  				</div>
  				<div class="form-group">
    				<label for="code">代码</label>
    				<input type="text" class="form-control" id="code" name="code" placeholder="代码"/>
  				</div>
  				<input type="button" id="btn_query" value="查询" class="btn btn-success"/>
				<input type="button" id="btn_add" value="添加" class="btn btn-success"/>
			</form>
		</div>
		<div role="result">
			<div role="list">
      			<table id="list" class="table table-bordered table-condensed table-hover">
        			<thead>
          				<tr>
            				<th>名称</th>
            				<th>代码</th>
            				<th>备注</th>
            				<th>操作</th>
          				</tr>
        			</thead>
        			<tbody>
          				<tr>
            				<td>名称1</td>
            				<td>代码2</td>
            				<td>备注3</td>
            				<td></td>
          				</tr>
          				<tr>
            				<td>名称1</td>
            				<td>代码2</td>
            				<td>备注3</td>
            				<td></td>
          				</tr>
          				<tr>
            				<td>名称1</td>
            				<td>代码2</td>
            				<td>备注3</td>
            				<td></td>
          				</tr>
        			</tbody>
      			</table>
			</div>
			<div role="paging">
			
			</div>
		</div>
	</div>
	<div class="modal fade" id="add_modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modalLabel">添加</h4>
				</div>
				<div class="modal-body">
					<form id="frm_add">
					
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="save()">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>