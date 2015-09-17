package com.afeey.permission.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class DefaultRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		String username = (String) super.getAvailablePrincipal(principals);
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

		if (null != username && "admin".equals(username)) {
			simpleAuthorInfo.addRole("admin");
			simpleAuthorInfo.addStringPermission("perm");
			return simpleAuthorInfo;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken t = (UsernamePasswordToken) token;

		if ("admin".equals(t.getUsername())) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("admin",
					"123456", this.getName());
			this.setSession("username", t.getUsername());
			return authcInfo;
		}
		return null;
	}

	/**
	 * 数据保存到Shiro的Session中
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 * @param key 键
	 * @param value 值
	 */
	private void setSession(Object key, Object value) {
		Subject subject = SecurityUtils.getSubject();
		if (null != subject) {
			Session session = subject.getSession();
			System.out.println("session default timout " + session.getTimeout() + "ms");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

}
