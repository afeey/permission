package com.afeey.permission.controller.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/json")
public class LoginJsonController {

	private static final Logger log = LoggerFactory
			.getLogger(LoginJsonController.class);

	@RequestMapping("/login_result")
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String failure = request.getAttribute("shiroLoginFailure") != null ? (String) request
				.getAttribute("shiroLoginFailure") : null;
				
		boolean success = false;
		String msg = "";
		Subject subject=SecurityUtils.getSubject();
		if (null == failure) {
			// 登录成功
			if(!subject.isAuthenticated()){
				success=false;
				msg = "未登录";
			}else{
				success=true;
				msg = "登录成功";
				log.debug("user {} login success", subject.getPrincipal());
			}
		}else {
			// 登录失败
			success=false;
			if (UnknownAccountException.class.getName().equals(failure)) {
				msg = "用户名或密码错误";
			} else if (IncorrectCredentialsException.class.getName()
					.equals(failure)) {
				msg = "用户名或密码错误";
			} else {
				msg = "其他错误：" + failure;
			}
			log.debug("user {} login failure", subject.getPrincipal());
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("{\"success\":" + success + ",\"msg\":\"" + msg + "\"}");
		out.flush();
		out.close();
	}
}
