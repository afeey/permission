package com.afeey.permission.controller.view;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
	 * @param model model
	 * @param session Session
	 * @return view
	 */
	@RequestMapping("/")
	public String index(Model model, HttpSession session) {
		
		Subject subject=SecurityUtils.getSubject();
		model.addAttribute("username",subject.getPrincipal());
		return "index";
	}
	
}
