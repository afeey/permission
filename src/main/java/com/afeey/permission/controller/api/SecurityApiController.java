package com.afeey.permission.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.afeey.permission.core.po.User;
import com.afeey.permission.core.service.IUserService;

/**
 * 安全控制器
 * 
 * @author wyf
 *
 */
@Controller
@ResponseBody
@RequestMapping("/api")
public class SecurityApiController {

	private static final Logger log = LoggerFactory.getLogger(SecurityApiController.class);

	@Autowired
	private IUserService userService;

	/**
	 * 用户登录
	 * @param request request
	 * @return json
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public Object login(HttpServletRequest request) {
		boolean success = false;
		String msg = "";
		
		Subject subject=SecurityUtils.getSubject();
		try {
			
			// 如果已经登录，先执行注销，然后执行登录
			if(subject.isAuthenticated()){
				log.debug("{} logout", subject.getPrincipal());
				subject.logout();
			}
			
			// 创建令牌执行登录
			boolean rememberMe = false;
			String username = StringUtils.clean(request.getParameter("username"));
	        String password = StringUtils.clean(request.getParameter("password"));
	        String host = request.getRemoteHost();
	        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe, host);
	        subject.login(token);

	        // 保存Session
	        User user = userService.getByUserName(username);
	        Session session = subject.getSession();
	        session.setAttribute("userid",user.getId());
	        
	        success = true;
	        msg = "登录成功";
	        log.debug("{} login success. sessionid : {}", subject.getPrincipal(),subject.getSession().getId());
	        
		} catch (UnknownAccountException e) {
			msg = "用户名或密码错误";
			log.debug("login failure : {}",e.getMessage());
		} catch (IncorrectCredentialsException e) {
			msg = "用户名或密码错误";
			log.debug("login failure : {}",e.getMessage());
		} catch (Exception e) {
			msg = "其他错误：" + e.getMessage();
			log.debug("login failure : {}",e.getMessage());
		}
		
		Result result=new Result();
		result.setSuccess(success);
		result.setMessage(msg);
		
		return result;
	}
	
	/**
	 * 用户注销
	 * @return json
	 */
	@RequestMapping(value = "/logout")
	public Object logout(){
		
		boolean success = false;
		String msg = "";
		
		Subject subject = SecurityUtils.getSubject();
		try {
			if (subject.isAuthenticated()) {
				String principal = (String)subject.getPrincipal();
				subject.logout();
				log.debug("{} logout success", principal);
			}
			msg = "注销成功";
			success=true;
			log.debug("logout success");
		} catch (Exception e) {
			msg = "注销失败";
			success=false;
			log.debug("logout exception : {}", e.getMessage());
		}

		Result result=new Result();
		result.setSuccess(success);
		result.setMessage(msg);
		
		return result;
	}
	
	/**
	 * 未认证（未登录）
	 * @return json
	 */
	@RequestMapping(value = "/unauthenticated")
	public Object unauthenticated(){
		boolean success = false;
		String msg = "未登录或会话已过期";
		
		Result result=new Result();
		result.setSuccess(success);
		result.setCode(401);
		result.setMessage(msg);
		
		return result;
	}
	
	/**
	 * 没有权限
	 * @return json
	 */
	@RequestMapping(value = "/unauthorized")
	public Object unauthorized(){
		boolean success = false;
		String msg = "没有权限";
		
		Result result=new Result();
		result.setSuccess(success);
		result.setMessage(msg);
		
		return result;
	}
	
}
