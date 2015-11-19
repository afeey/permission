package com.afeey.permission.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
