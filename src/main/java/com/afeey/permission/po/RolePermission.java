package com.afeey.permission.po;

import java.util.Date;

/**
 * 角色权限
 */
public class RolePermission {

	/**
	 * ID
	 */
	private String id;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 权限ID
	 */
	private String perId;

	/**
	 * 创建时间
	 */
	private Date createAt;

	/**
	 * 角色
	 */
	private Role role;

	/**
	 * 权限
	 */
	private Permission permission;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPerId() {
		return perId;
	}

	public void setPerId(String perId) {
		this.perId = perId;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

}
