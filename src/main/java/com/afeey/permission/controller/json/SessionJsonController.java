package com.afeey.permission.controller.json;

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
 * @author 王跃飞
 *
 */
@Controller
@ResponseBody
@RequestMapping("/json")
public class SessionJsonController {

	@Autowired
	private SessionDAO sessionDAO;
	
	@RequestMapping(value="/session_list")
	public String sessionList() {
		
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		Session[] sessionArray = sessions.toArray(new Session[0]);
		
		JsonResult result=new JsonResult();
		result.setCode("200");
		result.setMessage("查询成功");
		result.setData(sessionArray);
		
		return result.toString();
	}
	
	@RequestMapping(value="/session")
	public String session() {

		Session session = SecurityUtils.getSubject().getSession(false);
		
		JsonResult result=new JsonResult();
		result.setCode("200");
		result.setMessage("查询成功");
		result.setData(session);
		
		return result.toString();
	}
}
