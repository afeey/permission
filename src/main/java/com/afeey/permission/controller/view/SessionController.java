package com.afeey.permission.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色控制器
 * 
 * @author afeey
 *
 */
@Controller
@RequestMapping("/admin")
public class SessionController {

	/**
	 * 会话列表
	 * @param model model
	 * @return view
	 */
	@RequestMapping({"/session","/session/list"})
	public String list(Model model) {
		return "admin/session/session_list";
	}
	
	/**
	 * 当前会话
	 * @param model model
	 * @return view
	 */
	@RequestMapping("/session/current")
	public String current(Model model) {
		return "admin/session/session_current";
	}
}
