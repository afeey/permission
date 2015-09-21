package com.afeey.permission.controller.view;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页控制器
 * 
 * @author afeey
 *
 */
@Controller
public class IndexController {

	/**
	 * 首页
	 * URL:/
	 * @return String
	 */
	@RequestMapping("/")
	public String index(Model model, HttpSession session) {
		model.addAttribute("sessionid", session.getId());

		return "index";
	}
	
	/**
	 * 未授权
	 * @return
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "unauthorized";
	}
}
