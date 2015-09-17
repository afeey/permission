package com.afeey.permission.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 授权控制器
 * 
 * @author afeey
 *
 */
@Controller
public class AuthorizationController {

	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "unauthorized";
	}
}
