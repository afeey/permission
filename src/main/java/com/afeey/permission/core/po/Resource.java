package com.afeey.permission.core.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.afeey.permission.core.enums.ResourceTypeEnum;
import com.afeey.permission.core.enums.UseStatusEnum;

/**
 * 资源实体类
 * 
 * @author yjl
 *
 */
public class Resource {

	/**
	 * ID
	 */
	private String id;

	/**
	 * 父ID
	 */
	private String parentId;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 类型
	 */
	private ResourceTypeEnum type;

	/**
	 * Url
	 */
	private String url;

	/**
	 * 权限
	 */
	private String permissions;

	/**
	 * 状态
	 */
	private UseStatusEnum status;

	/**
	 * 备注
	 */
	private String comment;

	/**
	 * 创建时间
	 */
	private Date createAt;

	/**
	 * 更新时间
	 */
	private Date updateAt;

	/**
	 * 权限列表
	 */
	private List<String> permissionList;

	public Resource() {
		this.id = "";
		this.parentId = "";
		this.name = "";
		this.type = ResourceTypeEnum.DEFAULT;
		this.comment = "";
		this.url = "";
		this.permissions = "";
		this.status = UseStatusEnum.ENABLE;
		this.createAt = new Date();
		this.updateAt = new Date();
		this.permissionList = new ArrayList<String>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ResourceTypeEnum getType() {
		return type;
	}

	public void setType(ResourceTypeEnum type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UseStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UseStatusEnum status) {
		this.status = status;
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
