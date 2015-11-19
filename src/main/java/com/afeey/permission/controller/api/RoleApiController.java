package com.afeey.permission.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.afeey.permission.core.exception.CodeExistException;
import com.afeey.permission.core.exception.NameExistException;
import com.afeey.permission.core.po.Role;
import com.afeey.permission.core.service.IPage;
import com.afeey.permission.core.service.IRoleService;

/**
 * 角色控制器
 * 
 * @author wyf
 *
 */
@Controller
@ResponseBody
@RequestMapping("/api/role")
public class RoleApiController {
	
	private static final Logger log = LoggerFactory.getLogger(RoleApiController.class);

	@Autowired
	private IRoleService roleService;

	@RequestMapping(value = "/add")
	public Object add(HttpServletRequest request){
		
		boolean success=false;
		String msg="";
	
		String name = request.getParameter("name")==null?"":request.getParameter("name").trim();
		String code = request.getParameter("code")==null?"":request.getParameter("code").trim();
		String comment = request.getParameter("comment")==null?"":request.getParameter("comment").trim();
		
		Role role=new Role();
		role.setName(name);
		role.setCode(code);
		role.setComment(comment);
		try {
			roleService.add(role);
			success = true;
			msg = "保存成功";
		} catch (NameExistException e) {
			success = false;
			msg = "名称已存在";
		} catch (CodeExistException e) {
			success = false;
			msg = "代码已存在";
		} catch (Exception e) {
			success = false;
			msg = e.getMessage();
		}

		Result result = new Result();
		result.setSuccess(success);
		result.setMessage(msg);

		return result;
	}
	
	@RequestMapping(value = "/update")
	public Object update(HttpServletRequest request){
		
		boolean success=false;
		String msg="";
	
		String id = request.getParameter("id")==null?"":request.getParameter("id").trim();
		String name = request.getParameter("name")==null?"":request.getParameter("name").trim();
		String code = request.getParameter("code")==null?"":request.getParameter("code").trim();
		String comment = request.getParameter("comment")==null?"":request.getParameter("comment").trim();
		
		try {
			Role role=roleService.getById(id);
			role.setName(name);
			role.setCode(code);
			role.setComment(comment);
			roleService.update(role);
			success = true;
			msg = "保存成功";
		} catch (NameExistException e) {
			success = false;
			msg = "名称已存在";
		} catch (CodeExistException e) {
			success = false;
			msg = "代码已存在";
		} catch (Exception e) {
			success = false;
			msg = e.getMessage();
		}

		Result result = new Result();
		result.setSuccess(success);
		result.setMessage(msg);

		return result;
	}
	
	@RequestMapping(value = "/delete")
	public Object delete(@RequestParam("id") String id){
		
		boolean success=false;
		String msg="";
		
		try{
			roleService.delete(id);
			success = true;
			msg = "删除成功";
		}catch(Exception e){
			success = false;
			msg = e.getMessage();
		}
		
		Result result = new Result();
		result.setSuccess(success);
		result.setMessage(msg);

		return result;
	}
	
	@RequestMapping(value = "/query")
	public Object query(@RequestParam("id") String id){
		
		boolean success=false;
		String msg="";
		
		Role role = roleService.getById(id);
		success = true;
		msg = "查询成功";
		Result result = new Result();
		result.setSuccess(success);
		result.setMessage(msg);
		result.setData(role);

		return result;
	}
	
	/**
	 * 角色列表
	 * @param name 名称
	 * @param code 代码
	 * @param start 起始索引
	 * @param length 记录数
	 * @return json
	 */
	@RequestMapping(value = "/list")
	public Object list(
			@RequestParam("name") String name, 
			@RequestParam("code") String code,
			@RequestParam("start") Integer start, 
			@RequestParam("length") Integer length){
		
		log.debug("name:{},code:{},start:{},length:{}", name, code, start, length);

		int number = start / length + 1;
		IPage<Role> page = roleService.list(name, code, null, number, length);

		Result result = new Result();
		result.setDraw(0);
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		result.setData(page.getList());

		return result;
	}

}
