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
	 * 角色管理
	 * URL:/admin/role
	 * @param model model
	 * @return view
	 */
	@RequestMapping("/role")
	public String region(Model model) {
		
		return "admin/role/role_list";
	}
}
