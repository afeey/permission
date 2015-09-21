package com.afeey.permission.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 资源控制器
 * 
 * @author afeey
 *
 */
@Controller
@RequestMapping("/admin")
public class ResourceController {

	/**
	 * 地区管理
	 * URL:/admin/resource
	 * @param model
	 * @return String
	 */
	@RequestMapping("/resource")
	public String region(Model model) {
		
		return "admin/resource/resource";
	}
}
