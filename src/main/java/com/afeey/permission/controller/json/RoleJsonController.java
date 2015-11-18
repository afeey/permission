package com.afeey.permission.controller.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.afeey.permission.core.po.Role;
import com.afeey.permission.core.service.IRoleService;

/**
 * 角色控制器
 * 
 * @author wyf
 *
 */
@Controller
@ResponseBody
@RequestMapping("/json/role")
public class RoleJsonController {

	@Autowired
	private IRoleService roleService;
	

	@RequestMapping(value = "/list")
	public Object list(){
		
		boolean success = false;
		String msg = "";
		
		List<Role> list = roleService.list(null, null, null);

		JsonResult result=new JsonResult();
		result.setCode(success ? "200" : "401");
		result.setMessage(msg);
		result.setData(list);
		
		return result;
	}
	
	
}
