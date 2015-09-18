package com.afeey.permission.controller.view;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 地区控制器
 * 
 * @author afeey
 *
 */
@Controller
public class RegionController {

	@RequestMapping("/region")
	public String region(Model model) {
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		model.addAttribute("username", username);

		return "region/list";
	}
}
