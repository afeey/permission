package com.afeey.permission.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 安全控制器
 * 
 * @author 王跃飞
 *
 */
@Controller
public class LoginController {
	
	/**
	 * 登录
	 * @param model model
	 * @param request request
	 * @return view
	 */
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request) {
			return "login";
	}
	
	@RequestMapping("/logined")
	public String logined() {
		return "redirect:/";
	}

	@RequestMapping("/login_success")
	public String loginSuccess() {
		return "forward:json/login_result";
	}

	@RequestMapping("/login_failure")
	public String loginFailure() {
		return "forward:json/login_result";
	}
}
