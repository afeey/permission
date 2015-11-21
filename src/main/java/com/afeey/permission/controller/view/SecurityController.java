package com.afeey.permission.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 安全控制器
 * 
 * @author wyf
 *
 */
@Controller
public class SecurityController {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityController.class);
	
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
	 * 未认证
	 * @param request HttpServletRequest
	 * @return view
	 */
	@RequestMapping("/unauthenticated")
	public String unauthenticated(HttpServletRequest request) {
		return "not_login";
	}
	
	/**
	 * 未授权
	 * @param request HttpServletRequest
	 * @return view
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized(HttpServletRequest request) {
		return "unauthorized";
	}
}
