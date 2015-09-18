package com.afeey.permission.controller.view;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理控制器
 * 
 * @author afeey
 *
 */
@Controller
public class AdminController {

	@RequestMapping({ "/admin", "/admin/index" })
	public String manage(Model model) {
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		model.addAttribute("username", username);

		return "admin/index";
	}
}
