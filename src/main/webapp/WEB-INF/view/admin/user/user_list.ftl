<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>用户列表</title>
	
	<link rel="stylesheet" href="${rc.contextPath}/js/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${rc.contextPath}/js/datatables/css/dataTables.bootstrap.min.css" />
	<link rel="stylesheet" href="${rc.contextPath}/css/theme.min.css" />
</head>
<body>
	<ol class="breadcrumb">
  		<li><a href="#">权限管理</a></li>
  		<li class="active">用户列表</li>
	</ol>
	<div role="main" class="container-fluid">
		<div role="action">
			<form class="form-inline" id="frm_action">
				<div class="form-group">
    				<label for="f_userName">用户名</label>
    				<input type="text" class="form-control" id="f_userName" name="f_userName"/>
  				</div>
  				<div class="form-group">
    				<label for="f_roleId">角色</label>
    				<select class="form-control" id="f_roleId" name="f_roleId">
    					<option value="">全部</option>
    				<#list roles as role>
    					<option value="${role.id}">${role.name}</option>
    				</#list>
    				</select>
  				</div>
  				<button type="button" id="btn_query" class="btn btn-success">查询</button>
				<button type="button" id="btn_add" class="btn btn-success">添加</button>
			</form>
		</div>
      	<table id="list" class="table table-bordered table-condensed table-hover" width="100%">
        	<thead>
          		<tr>
            		<th>用户名</th>
            		<th>昵称</th>
            		<th>角色</th>
            		<th>姓名</th>
            		<th>手机号</th>
            		<th>邮箱</th>
            		<th>性别</th>
            		<th>注册时间</th>
            		<th>最近登录</th>
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
						<div class="col-sm-3 text-right">名称 :</div>
						<div class="col-sm-9" id="v_name"></div>
					</div>
					<div class="row">
						<div class="col-sm-3 text-right">代码 :</div>
						<div class="col-sm-9" id="v_code"></div>
					</div>
					<div class="row">
						<div class="col-sm-3 text-right">备注 :</div>
						<div class="col-sm-9" id="v_comment""></div>
					</div>
					<div class="row">
						<div class="col-sm-3 text-right">创建时间 :</div>
						<div class="col-sm-9" id="v_createAt""></div>
					</div>
					<div class="row">
						<div class="col-sm-3 text-right">更新时间 :</div>
						<div class="col-sm-9" id="v_updateAt""></div>
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
					<form id="frm_add" class="form-horizontal">
  						<div class="form-group">
    						<label for="s_name" class="col-sm-2 control-label"><span class="required">*</span>名称</label>
    						<div class="col-sm-9">
    							<input type="text" class="form-control" id="s_name" name="s_name"/>
    						</div>
  						</div>
  						<div class="form-group">
    						<label for="s_code" class="col-sm-2 control-label"><span class="required">*</span>代码</label>
    						<div class="col-sm-9">
    							<input type="text" class="form-control" id="s_code" name="s_code"/>
    						</div>
  						</div>
  						<div class="form-group">
    						<label for="s_comment" class="col-sm-2 control-label">备注</label>
    						<div class="col-sm-9">
    							<textarea class="form-control" id="s_comment" name="s_comment"></textarea>
  							</div>
  						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" id="btn_add_save">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改模态 -->
	<div class="modal fade" id="update_modal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改</h4>
				</div>
				<div class="modal-body">
					<div class="loading">数据加载中...</div>
					<form id="frm_update" class="form-horizontal">
						<input type="hidden" id="u_id" name="u_id" value=""/>
  						<div class="form-group">
    						<label for="u_name" class="col-sm-2 control-label"><span class="required">*</span>名称</label>
    						<div class="col-sm-9">
    							<input type="text" class="form-control" id="u_name" name="u_name"/>
    						</div>
  						</div>
  						<div class="form-group">
    						<label for="u_code" class="col-sm-2 control-label"><span class="required">*</span>代码</label>
    						<div class="col-sm-9">
    							<input type="text" class="form-control" id="u_code" name="u_code"/>
							</div>  						
  						</div>
  						<div class="form-group">
    						<label for="u_comment" class="col-sm-2 control-label">备注</label>
    						<div class="col-sm-9">
    							<textarea class="form-control" id="u_comment" name="u_comment"></textarea>
    						</div>
  						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" id="btn_update_save">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- jquery -->
	<script type="text/javascript" src="${rc.contextPath}/js/jquery.min.js"></script>
	<!-- bootstrap -->
	<script type="text/javascript" src="${rc.contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
	<!-- datatables -->
	<script type="text/javascript" src="${rc.contextPath}/js/datatables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/js/datatables/js/dataTables.bootstrap.min.js"></script>
	<!-- jquery_validation -->
	<script type="text/javascript" src="${rc.contextPath}/js/jquery_validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/js/jquery_validation/localization/messages_zh.min.js"></script>
	<!-- system -->
	<script type="text/javascript" src="${rc.contextPath}/js/system/common.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//绑定事件
  			$('#btn_query').click(query);
  			$('#btn_add').click(showAdd);
  			$('#btn_add_save').click(add);
  			$('#btn_update_save').click(update);
  			
  			//初始化
  			initTable();
  			initValidate();
		});
		
		var table; //表格
		//初始化表格
		function initTable(){
			table =
			$('#list').DataTable({
        		serverSide: true,
        		ajax: function (data, callback, settings) {
        			data.userName = $.trim($("#f_userName").val());
        			data.roleId = $.trim($("#f_roleId").val());
        			$.ajax({
        				type: "post",
        				url: "${rc.contextPath}/api/user/list",
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
                    { data: "userName" },
                    { data: "alias" },
                    { data: "roles" },
                    { data: "fullName" },
                    { data: "cellphone" },
                    { data: "mail" },
                    { data: "sex" },
                    { data: "regTime" },
                    { data: "loginTime" },
                    { 
                      data: "id",
                      createdCell: function (td,val,data,row,col) {
                        var html='<div class="btn-group" role="group" >';
						html += '	<a type="button" class="btn btn-primary" onclick="showView(\''+ val +'\')">详细</a>';
						html += '	<a type="button" class="btn btn-primary" onclick="showUpdate(\''+ val +'\')">修改</a>';
						html += '	<a type="button" class="btn btn-primary" onclick="deleteItem(\''+ val +'\')">删除</a>';
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
    	
    	//初始化验证
    	function initValidate() {
			$("#frm_add").validate({
				ignore:'',
		        rules: {
		        	s_name: {
		        		required: true,
		                maxlength: 20
		            },
		            s_code:{
		                required: true,
		                maxlength: 20
		            },
		            s_comment:{
		                maxlength: 100
		            }
		        },
		        messages: {
		        	s_name: {
		        		required: "名称不能为空",
		                maxlength: "不可超过{0}个字符"
		            },
		            s_code:{
		                required: "代码不能为空",
		                maxlength: "不可超过{0}个字符"
		            },
		            s_comment:{
		                maxlength: "备注不可超过{0}个字符"
		            }
		        },
		        errorPlacement: function (error, element) {
		        	error.insertAfter(element);
		        }
			});	
			
			$("#frm_update").validate({
				ignore:'',
		        rules: {
		        	u_name: {
		        		required: true,
		                maxlength: 20
		            },
		            u_code:{
		                required: true,
		                maxlength: 20
		            },
		            u_comment:{
		                maxlength: 100
		            }
		        },
		        messages: {
		        	u_name: {
		        		required: "名称不能为空",
		                maxlength: "不可超过{0}个字符"
		            },
		            u_code:{
		                required: "代码不能为空",
		                maxlength: "不可超过{0}个字符"
		            },
		            u_comment:{
		                maxlength: "备注不可超过{0}个字符"
		            }
		        },
		        errorPlacement: function (error, element) {
		        	error.insertAfter(element);
		        }
			});	
		}
    	
    	//查询
		function query(){
			table.ajax.reload();
		}
		
		//显示详细
		function showView(id){
			$("#view_modal").modal('show');
			$(".modal-body .row div[id^='v_']").html("");
			var vo={};
			vo.id=id;
			$.ajax({
    			type: "get",
    			url: "${rc.contextPath}/api/user/query",
    			data: vo,
    			dataType: "json",
     			success: function(result){
     				if(!result.success){
						alert(result.message);
						return;
					}
					var _obj = result.data;
					$("#v_name").html(_obj.name);
					$("#v_code").html(_obj.code);
					$("#v_comment").html(_obj.comment);
					$("#v_createAt").html(new Date(_obj.createAt).Format('yyyy-MM-dd hh:mm:ss'));
					$("#v_updateAt").html(new Date(_obj.updateAt).Format('yyyy-MM-dd hh:mm:ss'));
    			}
    		});
		}
		
		//显示添加
		function showAdd(){
			$("#add_modal label.error").remove();
			$("#add_modal input[type='text'],#add_modal textarea").val("");
			$("#add_modal").modal('show');
		}
		
		//添加
		function add(){
			if(!$("#frm_add").valid()){
				$("#add_modal .alert").show();
				return;
			}
		
			var vo={};
			vo.name=$.trim($("#s_name").val());
			vo.code=$.trim($("#s_code").val());
			vo.comment=$.trim($("#s_comment").val());
			$.ajax({
    			type: "post",
    			url: "${rc.contextPath}/api/user/add",
    			data: vo,
    			dataType: "json",
     			success: function(result){
     				if(!result.success){
     					alert(result.message);
     					return;
     				}
     				alert('保存成功');
     				$('#add_modal').modal('hide');
     				table.ajax.reload(null,false);
    			}
    		});
		}
		
		//显示修改
		function showUpdate(id){
		
			//显示
			$("#update_modal .loading").show();
			$("#update_modal form").hide();
			$("#update_modal").modal('show');
			$("#update_modal label.error").remove();
			$("#update_modal input[type='text'],#update_modal textarea").val("");
			
			//加载数据
			var vo={};
			vo.id=id;
			$.ajax({
    			type: "get",
    			url: "${rc.contextPath}/api/user/query",
    			data: vo,
    			dataType: "json",
     			success: function(result){
     				if(!result.success){
						alert(result.message);
						return;
					}
					var _obj = result.data;
					$("#u_id").val(_obj.id);
					$("#u_name").val(_obj.name);
					$("#u_code").val(_obj.code);
					$("#u_comment").val(_obj.comment);
					
					$("#update_modal .loading").hide();
					$("#update_modal form").show();
    			}
    		});
		}
		
		//修改
		function update(){
			if(!$("#frm_update").valid()){
				$("#frm_update .alert.").show();
				return;
			}
		
			var vo={};
			vo.id=$.trim($("#u_id").val());
			vo.name=$.trim($("#u_name").val());
			vo.code=$.trim($("#u_code").val());
			vo.comment=$.trim($("#u_comment").val());
			$.ajax({
    			type: "post",
    			url: "${rc.contextPath}/api/user/update",
    			data: vo,
    			dataType: "json",
     			success: function(result){
     				if(!result.success){
     					alert(result.message);
     					return;
     				}
     				alert('修改成功');
     				$('#update_modal').modal('hide');
     				table.ajax.reload(null,false);
    			}
    		});
		}
		
		//删除
		function deleteItem(id){
			if (!confirm("确认要删除？")) {
		    	return;
		    }
		
			var vo={};
			vo.id=id;
			$.ajax({
    			type: "get",
    			url: "${rc.contextPath}/api/user/delete",
    			data: vo,
    			dataType: "json",
     			success: function(result){
     				if(!result.success){
						alert(result.message);
						return;
					}
					alert("删除成功");
					if(table.ajax.json().data.length==1){
						table.page('previous').ajax.reload(null,false);
					}else{
						table.ajax.reload(null,false);
					}
    			}
    		});
		}
	</script>
</body>
</html>