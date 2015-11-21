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
	
	private String unauthenticatedUrl = "/unauthenticated"; 		// 未认证Url
	private String apiUrl = "/api"; 								// api起始路径
	private String apiUnauthenticatedUrl = "/api/unauthenticated"; 	// api跳转的未认证Url

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		return subject.isAuthenticated();
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = null;
		if ((request instanceof HttpServletRequest)) {
			httpRequest = (HttpServletRequest) request;
		}else{
			log.error("request is not HttpServletRequest");
		}
		
		if (apiUrl.trim().length() > 0
				&& httpRequest.getRequestURI().startsWith(request.getServletContext().getContextPath()
								.concat(apiUrl).concat("/"))) {
			httpRequest.getRequestDispatcher(apiUnauthenticatedUrl).forward(request, response);
		} else {
			httpRequest.getRequestDispatcher(unauthenticatedUrl).forward(request, response);
		}
		return false;
	}
	
	public String getUnauthenticatedUrl() {
		return unauthenticatedUrl;
	}

	public void setUnauthenticatedUrl(String unauthenticatedUrl) {
		this.unauthenticatedUrl = unauthenticatedUrl;
	}
	
	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getApiUnauthenticatedUrl() {
		return apiUnauthenticatedUrl;
	}

	public void setApiUnauthenticatedUrl(String apiUnauthenticatedUrl) {
		this.apiUnauthenticatedUrl = apiUnauthenticatedUrl;
	}
}
