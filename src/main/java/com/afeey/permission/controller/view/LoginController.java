package com.afeey.permission.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制器
 * 
 * @author wyf
 *
 */
@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 登录
	 * @param model model
	 * @param request request
	 * @return view
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * 登录
	 * @param model model
	 * @param request request
	 * @return view
	 */
	@RequestMapping("/logout")
	public String logout() {
		
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			String principal = (String)subject.getPrincipal();
			subject.logout();
			log.debug("{} logout success", principal);
		}
		
		return "redirect:/";
	}
	
	/**
	 * 未授权
	 * @return view
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request) {
		String uri = request.getRequestURI();
		log.info(uri);
		return "unauthorized";
	}
}
