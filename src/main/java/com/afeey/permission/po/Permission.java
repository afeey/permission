package com.afeey.permission.po;

import com.afeey.permission.enums.UseStatusEnum;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 权限
 */
public class Permission {

	/**
	 * ID
	 */
	@NotNull
	private String id;

	/**
	 * 父ID
	 */
	@NotNull
	private String parentId;

	/**
	 * 名称
	 */
	@NotNull
	@Min(2)
	private String name;

	/**
	 * 代号
	 */
	private String code;

	/**
	 * 备注
	 */
	private String comment;

	/**
	 * 路径
	 */
	private String url;

	/**
	 * 使用状态
	 */
	private UseStatusEnum status;

	/**
	 * 排序
	 */
	private int sort;

	/**
	 * 创建时间
	 */
	private Date createAt;

	/**
	 * 更新时间
	 */
	private Date updateAt;

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

}
