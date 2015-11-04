package com.afeey.permission.controller.view;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger log = LoggerFactory
			.getLogger(IndexController.class);

	/**
	 * 首页
	 * @param model model
	 * @param session Session
	 * @return view
	 */
	@RequestMapping("/")
	public String index(Model model, HttpSession session) {
		
		Enumeration<String> names= session.getAttributeNames();
		log.debug("session attribute name:");
		while (names.hasMoreElements()) {
			String name=(String) names.nextElement();
			log.debug("name:{} , value:{}",name,session.getAttribute(name));
		}
		
		Subject subject=SecurityUtils.getSubject();
		model.addAttribute("username",subject.getPrincipal());
		model.addAttribute("sessionid", session.getId());
		return "index";
	}
	
	/**
	 * 未授权
	 * @return view
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "unauthorized";
	}
}
