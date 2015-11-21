package com.afeey.permission.controller.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
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
		
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		if(sessions!=null&&sessions.size()>0){
			Session[] sessionArray = sessions.toArray(new Session[0]);

			for (Session session : sessionArray) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", session.getId().toString());
				map.put("userid",session.getAttribute("userid")==null?"" : session.getAttribute("userid").toString());
				map.put("username", session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY")==null?"":
						((SimplePrincipalCollection) session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY")).getPrimaryPrincipal().toString());
				map.put("host", session.getHost());
				map.put("timeout", Long.toString(session.getTimeout()));
				map.put("startTimestamp", new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(session
						.getStartTimestamp()));
				map.put("lastAccessTime", new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(session
						.getLastAccessTime()));

				list.add(map);
			}
		}

		Result result = new Result();
		result.setRecordsTotal(list.size());
		result.setRecordsFiltered(list.size());
		result.setData(list);

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
