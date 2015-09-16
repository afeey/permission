package com.afeey.permission.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理控制器
 * 
 * @author afeey
 *
 */
@Controller
public class AdminController {

	@RequestMapping("/admin")
	public String manage() {

		return "admin/index";
	}
}
