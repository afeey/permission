package com.afeey.permission.po;

import com.afeey.permission.enums.UseStatusEnum;
import java.util.Date;

/**
 * 角色
 */
public class Role {

	/**
	 * ID
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 备注
	 */
	private String comment;

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
	 * 创建时间
	 */
	private Date updateAt;

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

}
