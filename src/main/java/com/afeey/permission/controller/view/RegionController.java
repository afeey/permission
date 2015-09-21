package com.afeey.permission.controller.view;

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
@RequestMapping("/admin")
public class RegionController {

	/**
	 * 地区管理
	 * URL:/admin/region
	 * @param model
	 * @return String
	 */
	@RequestMapping("/region")
	public String region(Model model) {
		
		return "admin/region/region";
	}
}
