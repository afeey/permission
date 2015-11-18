package com.afeey.permission.core.po;

import java.util.Date;

/**
 * 角色资源关联实体类
 * 
 * @author yjl
 *
 */
public class RoleResource {
	private String id;
	private String roleId;
	private String resourceId;
	private Date createAt;
	
	public RoleResource() {
		this.id = ""; 
		this.roleId = ""; 
		this.resourceId = ""; 
		this.createAt = new Date(); 
	}
	
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
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	} 
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	} 
}
