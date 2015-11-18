package com.afeey.permission.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 权限控制器
 * 
 * @author wyf
 *
 */
@Controller
@RequestMapping("/admin")
public class PermissionController {

	/**
	 * 权限管理
	 * URL:/admin/permission
	 * @param model model
	 * @return view
	 */
	@RequestMapping("/permission")
	public String region(Model model) {
		
		return "admin/permission/permission";
	}
}
