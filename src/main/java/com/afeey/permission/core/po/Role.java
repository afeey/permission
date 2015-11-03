package com.afeey.permission.core.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.afeey.permission.core.enums.UseStatusEnum;

/**
 * 角色实体类
 * 
 * @author 杨金龙
 *
 */
public class Role {
	private String id;
	private String name;
	private String code;
	private String comment;
	private UseStatusEnum status;
	private int sort;
	private Date createAt;
	private Date updateAt;
	private String permissions;
	private List<String> permissionList;

	public Role() {
		this.id = "";
		this.name = "";
		this.code = "";
		this.comment = "";
		this.status = UseStatusEnum.ENABLE;
		this.sort = 0;
		this.createAt = new Date();
		this.updateAt = new Date();
		this.permissions = "";
		this.permissionList = new ArrayList<String>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UseStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UseStatusEnum status) {
		this.status = status;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public List<String> getPermissionList() {
		permissionList.clear();
		if (permissions.trim().length() == 0) {
			return permissionList;
		}

		String[] permissionArrary = this.permissions.split(",");
		for (String permission : permissionArrary) {
			permissionList.add(permission);
		}
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		permissions = "";
		for (String permission : permissionList) {
			permissions += "," + permission;
		}
		if (permissions.length() > 0) {
			permissions = permissions.substring(1);
		}
		this.permissionList = permissionList;
	}
}
