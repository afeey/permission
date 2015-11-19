package com.afeey.permission.controller.api;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 会话控制器
 * 
 * @author wyf
 *
 */
@Controller
@ResponseBody
@RequestMapping("/api/session")
public class SessionApiController {

	@Autowired
	private SessionDAO sessionDAO;
	
	/**
	 * 会话列表
	 * @return json
	 */
	@RequestMapping(value="/list")
	public Object list() {
		
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		Session[] sessionArray = sessions.toArray(new Session[0]);
		
		Result result=new Result();
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.setData(sessionArray);
		
		return result;
	}
	
	/**
	 * 当前会话
	 * @return json
	 */
	@RequestMapping(value="/current")
	public Object current() {

		Session session = SecurityUtils.getSubject().getSession(false);
		
		Result result=new Result();
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.setData(session);
		
		return result;
	}
}
