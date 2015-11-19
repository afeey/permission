<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>角色列表</title>
	
	<link rel="stylesheet" href="${rc.contextPath}/js/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${rc.contextPath}/js/datatables/css/dataTables.bootstrap.min.css" />
	<link rel="stylesheet" href="${rc.contextPath}/css/theme.min.css" />
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
      	<table id="list" class="table table-bordered table-condensed table-hover" width="100%">
        	<thead>
          		<tr>
            		<th>名称</th>
            		<th>代码</th>
            		<th>备注</th>
            		<th style="width:180px;">操作</th>
          		</tr>
        	</thead>
      	</table>
	</div>
	
	<!-- 详细模态 -->
	<div class="modal fade" id="view_modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">详细</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-3 text-right">名称 :</div>
						<div class="col-md-9" id="v_name"></div>
					</div>
					<div class="row">
						<div class="col-md-3 text-right">代码 :</div>
						<div class="col-md-9" id="v_code"></div>
					</div>
					<div class="row">
						<div class="col-md-3 text-right">备注 :</div>
						<div class="col-md-9" id="v_comment""></div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 添加模态 -->
	<div class="modal fade" id="add_modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">添加</h4>
				</div>
				<div class="modal-body">
					<form id="frm_add">
					
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="btn_add_save">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- jquery -->
	<script src="${rc.contextPath}/js/jquery.min.js"></script>
	<!-- bootstrap -->
	<script type="text/javascript" src="${rc.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	<!-- datatables -->
	<script type="text/javascript" src="${rc.contextPath}/js/datatables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/js/datatables/js/dataTables.bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//绑定事件
  			$('#btn_query').click(query);
  			$('#btn_add').click(showAdd);
  			$('#btn_add_save').click(save);
  			
  			//初始化
  			initTable();
		});
		
		//初始化表格
		function initTable(){
			$('#list').DataTable({
        		serverSide: true,
        		ajax: {
        			type: "post",
        			url: "${rc.contextPath}/api/role/list",
        			data: function(d){
        				d.name = $.trim($("#name").val());
        				d.code = $.trim($("#code").val());
        			}
        		},
        		columns: [
                    { data: "name" },
                    { data: "code" },
                    { data: "comment" },
                    { 
                      data: "id",
                      createdCell: function (td,val,data,row,col) {
                        var html='<div class="btn-group" role="group" >';
						html += '	<button type="button" class="btn btn-primary" onclick="showView('+ val +')">详细</button>';
						html += '	<button type="button" class="btn btn-primary" onclick="showUpdate('+ val +')">修改</button>';
						html += '	<button type="button" class="btn btn-primary" onclick="deleteItem('+ val +')">删除</button>';
						html += '</div>';
                      
                      	$(td).html(html);
                      }
                    }
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
			$("#list").DataTable().draw();
		}
		
		//显示详细
		function showView(id){
			$("#view_modal input[type='text'],#view_modal textarea").val("");
			$("#view_modal").modal('show');
			
			
		}
		
		//显示添加
		function showAdd(){
			$("#add_modal input[type='text'],#add_modal textarea").val("");
			$("#add_modal").modal('show');
		}
		
		//保存
		function save(){
			var vo={};
			vo.name="role";
			vo.code="code";
			
			$.ajax({
    			type: "post",
    			url: "${rc.contextPath}/api/role/save",
    			data: vo,
    			dataType: "json",
     			success: function(result){
     				if(!result.success){
     					alert(result.message);
     					return;
     				}
     				$('#add_modal').modal('hide');
    			}
    		});
		}
		
		//显示修改
		function showUpdate(id){
			alert('修改成功')
		}
		
		//删除
		function deleteItem(id){
			alert('删除成功')
		}
	</script>
</body>
</html>