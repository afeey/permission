package com.afeey.permission.shiro.filter.authc;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 认证过滤器
 * 
 * @author wyf
 *
 */
public class AuthenticationFilter extends AccessControlFilter{

	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	private String unauthenticatedUrl = "/unauthenticated"; 		// 未认证转发Url

	/**
	 * 判断访问是否被允许
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		return subject.isAuthenticated();
	}
	
	/**
	 * 访问被拒绝调用方法
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = null;
		if ((request instanceof HttpServletRequest)) {
			httpRequest = (HttpServletRequest) request;
		}else{
			log.error("request is not HttpServletRequest");
		}
		
		httpRequest.getRequestDispatcher(unauthenticatedUrl).forward(request, response);
		
		return false;
	}
	
	public String getUnauthenticatedUrl() {
		return unauthenticatedUrl;
	}

	public void setUnauthenticatedUrl(String unauthenticatedUrl) {
		this.unauthenticatedUrl = unauthenticatedUrl;
	}
	
}
