package com.afeey.permission.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRealm extends AuthorizingRealm {

	private static final Logger log = LoggerFactory
			.getLogger(DefaultRealm.class);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		SimpleAuthorizationInfo authzInfo = null;
		String username = (String) super.getAvailablePrincipal(principals);

		if (null != username && "admin".equals(username)) {
			authzInfo = new SimpleAuthorizationInfo();
			
			authzInfo.addRole("admin");
			authzInfo.addStringPermission("resource");
			
			if(log.isDebugEnabled()){
				log.debug("doGetAuthorizationInfo");
			}
		}
		return authzInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		AuthenticationInfo authcInfo = null;
		UsernamePasswordToken t = (UsernamePasswordToken) token;

		if ("admin".equals(t.getUsername())) {
			authcInfo = new SimpleAuthenticationInfo("admin", "123456",
					this.getName());
			
			if(log.isDebugEnabled()){
				log.debug("doGetAuthenticationInfo");
			}
		}
		return authcInfo;
	}
}
