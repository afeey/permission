package com.afeey.permission.controller.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/json")
public class LoginJsonController {
	@RequestMapping("/login")
	public void login(HttpServletResponse response) throws IOException {
		
		response.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();  
        out.print("{\"err\":0,\"msg\":\"密码错误\"}");
        out.flush();
        out.close();
	}
}
