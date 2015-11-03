package com.afeey.permission.shiro.filter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 访问控制允许源过滤器<br>
 * 允许配置的源访问
 * 
 * @author 王跃飞
 *
 */
public class AccessControlAllowOriginFilter extends PathMatchingFilter {

	private static final Logger log = LoggerFactory.getLogger(AccessControlAllowOriginFilter.class);

	private List<String> hosts;

	@Override
	protected boolean onPreHandle(ServletRequest request,ServletResponse response, Object mappedValue) throws MalformedURLException {
		
		HttpServletRequest httpRequest = null;
		HttpServletResponse httpResponse = null;
		if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {
			httpRequest = (HttpServletRequest) request;
			httpResponse = (HttpServletResponse) response;
		}
		
		log.debug("request cookies:");
		Cookie[] cookies = httpRequest.getCookies();
		if(null!=cookies){
			for (Cookie cookie : cookies) {
				log.debug("name: {} , domain: {}, path: {}, value: {}",cookie.getName(),cookie.getDomain(),cookie.getPath(),cookie.getValue());
			}
		}
	
		String referer = httpRequest.getHeader("Referer");
		String origin = httpRequest.getHeader("Origin");
		log.debug("Referer : {}", referer);
		log.debug("Origin : {}", origin);
		
		if (origin == null || referer == null) {
			return true;
		}
	
		// 检测是否允许访问的源，如果是添加允许访问头
		for (String host : hosts) {
			URL url = new URL(referer);
			if (url.getHost().toLowerCase().equals(host.toLowerCase())) {
				httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
				httpResponse.setHeader("Access-Control-Allow-Origin", origin);
				log.debug("response setHeader: Access-Control-Allow-Credentials : true ");
				log.debug("response setHeader: Access-Control-Allow-Origin : {}", origin);
				break;
			}
		}
		return true;
	}
	
	public List<String> getHosts() {
		return hosts;
	}

	public void setHosts(List<String> hosts) {
		this.hosts = hosts;
	}

}
