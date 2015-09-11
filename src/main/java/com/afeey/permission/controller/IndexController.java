package com.afeey.permission.controller;

import org.springframework.stereotype.Controller;
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
	public String index() {
		return "index";
	}
}
