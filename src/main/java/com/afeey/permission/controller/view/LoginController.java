package com.afeey.permission.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 安全控制器
 * 
 * @author afeey
 *
 */
@Controller
public class LoginController {

	private static final Logger log = LoggerFactory
			.getLogger(LoginController.class);

	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request) {

		String failure = request.getAttribute("shiroLoginFailure") != null ? (String) request
				.getAttribute("shiroLoginFailure") : null;

		Subject subject = SecurityUtils.getSubject();
		if (null != failure) {
			log.debug("forward login_failure");
			return "forward:login_failure";
			
		} else if (subject.isAuthenticated()) {
			log.debug("forward logined");
			return "forward:logined";
			
		} else {
			log.debug("return login");
			return "login";
			
		}
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
