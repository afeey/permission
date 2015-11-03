package com.afeey.permission.shiro.realm;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.afeey.permission.core.po.Role;
import com.afeey.permission.core.po.User;
import com.afeey.permission.core.service.IUserService;

/**
 * 认证授权域
 * 
 * @author 王跃飞
 *
 */
public class AuthorizingRealm extends org.apache.shiro.realm.AuthorizingRealm {

	private static final Logger log = LoggerFactory
			.getLogger(AuthorizingRealm.class);

	@Autowired
	private IUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authzInfo = null;
		String username = (String) super.getAvailablePrincipal(principals);
		User user = userService.getByUserName(username);
		// 加载角色和权限
		if (null != user) {
			authzInfo = new SimpleAuthorizationInfo();
			List<Role> roleList = userService.listRoles(user.getId());
			List<String> permissionList = userService.listPermissions(user
					.getId());
			for (Role role : roleList) {
				authzInfo.addRole(role.getCode());
			}
			for (String permission : permissionList) {
				authzInfo.addStringPermission(permission);
			}

			if (log.isDebugEnabled()) {
				Set<String> roles = authzInfo.getRoles();
				if (null != roles) {
					log.debug("load roles {}", roles.size());
				}
				Set<String> perms = authzInfo.getStringPermissions();
				if (null != perms) {
					log.debug("load permissions {}", perms.size());
				}
			}
		}
		return authzInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		SimpleAuthenticationInfo authcInfo = null;
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		User user = userService.getByUserName(t.getUsername());
		if (null != user) {
			authcInfo = new SimpleAuthenticationInfo(user.getUserName(),
					user.getPassword(), this.getName());
			authcInfo.setCredentialsSalt(ByteSource.Util.bytes(t.getCredentials()));

			if (log.isDebugEnabled()) {
				log.debug("realm {} load authentication info. username:{}",
						this.getName(), t.getUsername());
			}
		}
		return authcInfo;
	}
}
