package com.afeey.permission.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.afeey.permission.core.po.Role;
import com.afeey.permission.core.service.IRoleService;

/**
 * 用户控制器
 * 
 * @author afeey
 *
 */
@Controller
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private IRoleService roleService;
	
	/**
	 * 用户管理
	 * URL:/admin/user
	 * @param model model
	 * @return view
	 */
	@RequestMapping({"/user","/user/list"})
	public String region(Model model) {
		
		List<Role> roleList = roleService.list(null, null, null);
		model.addAttribute("roles", roleList);
		
		return "admin/user/user_list";
	}
}
