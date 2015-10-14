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
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangjiamao.core.po.permission.Role;
import com.dangjiamao.core.po.permission.User;
import com.dangjiamao.core.service.permission.IUserService;

/**
 * 默认授权域
 * 
 * @author afeey
 *
 */
public class DefaultRealm extends AuthorizingRealm {

	private static final Logger log = LoggerFactory
			.getLogger(DefaultRealm.class);

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

		AuthenticationInfo authcInfo = null;
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		User user = userService.getByUserName(t.getUsername());
		if (null != user) {
			authcInfo = new SimpleAuthenticationInfo(user.getUserName(),
					user.getPassword(), this.getName());

			if (log.isDebugEnabled()) {
				log.debug("realm {} load authentication info. username:{}",
						this.getName(), t.getUsername());
			}
		}
		return authcInfo;
	}
}
