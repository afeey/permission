package com.afeey.permission.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色控制器
 * 
 * @author afeey
 *
 */
@Controller
@RequestMapping("/admin")
public class RoleController {

	/**
	 * 角色列表
	 * @param model model
	 * @return view
	 */
	@RequestMapping({"/role","/role/list"})
	public String list(Model model) {
		return "admin/role/role_list";
	}
}
