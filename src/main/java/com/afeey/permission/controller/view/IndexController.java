package com.afeey.permission.controller.view;

import javax.servlet.http.HttpSession;

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

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	/**
	 * 首页
	 * 
	 * @return String
	 */
	@RequestMapping({ "/", "/index" })
	public String index(Model model, HttpSession session) {
		if(log.isDebugEnabled()){
			log.debug("start index");
		}
		
		model.addAttribute("title", "首页");
		model.addAttribute("seesionid", session.getId());
		return "index";
	}
}
