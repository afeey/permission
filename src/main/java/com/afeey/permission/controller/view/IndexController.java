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
	 * 
	 * @return String
	 */
	@RequestMapping({ "/", "/index" })
	public String index(Model model, HttpSession session) {
		model.addAttribute("title", "首页");
		model.addAttribute("seesionid", session.getId());
		return "index";
	}
}
