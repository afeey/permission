package com.afeey.permission.core.po;

import java.util.Date;

/**
 * 用户角色关联实体类
 * 
 * @author 杨金龙
 *
 */
public class UserRole {
	private String id;
	private String userId;
	private String roleId;
	private Date createAt;
	
	public UserRole() {
		this.id = ""; 
		this.userId = ""; 
		this.roleId = ""; 
		this.createAt = new Date(); 
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	} 
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	} 
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	} 
}
