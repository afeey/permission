package com.afeey.permission.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户控制器
 * 
 * @author afeey
 *
 */
@Controller
@RequestMapping("/admin")
public class UserController {

	/**
	 * 用户管理
	 * URL:/admin/user
	 * @param model model
	 * @return view
	 */
	@RequestMapping("/user")
	public String region(Model model) {
		
		return "admin/user/user";
	}
}
