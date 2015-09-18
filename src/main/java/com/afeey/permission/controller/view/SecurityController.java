package com.afeey.permission.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
public class SecurityController {

	@RequestMapping("/login")
	public String login(Model model,HttpServletRequest request) {
		
		String className = (String)request.getAttribute("shiroLoginFailure");
        String error = "";
        if(UnknownAccountException.class.getName().equals(className)) {
            error = "用户名或密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(className)) {
            error = "用户名或密码错误";
        } else if(className != null) {
            error = "其他错误：" + className;
        }
        model.addAttribute("error", error);
        return "login";
	}
	
	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "unauthorized";
	}
}
