package com.afeey.permission.shiro.realm;

import java.util.Set;

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

/**
 * 默认授权域
 * 
 * @author afeey
 *
 */
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
			authzInfo.addStringPermission("permission");
			authzInfo.addStringPermission("resource");
			authzInfo.addStringPermission("role");
			authzInfo.addStringPermission("user");

			if (log.isDebugEnabled()) {
				Set<String> perms = authzInfo.getStringPermissions();
				log.debug("load permissions {}", perms.size());
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

			if (log.isDebugEnabled()) {
				log.debug("load authentication info. username:{}",
						t.getUsername());
			}
		}
		return authcInfo;
	}
}
