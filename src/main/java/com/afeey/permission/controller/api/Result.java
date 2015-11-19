package com.afeey.permission.controller.api;

/**
 * 列表结果
 * 
 * @author wyf
 *
 */
public class Result {
	
	/**
	 * 是否成功
	 */
	private boolean success;
	
	/**
	 * 返回提示
	 */
	private String message;
	
	/**
	 * 跳转链接
	 */
	private String redirect;
	
	/**
	 * 绘制
	 */
	private int draw;
	
	/**
	 * 记录总数
	 */
	private int recordsTotal;
	
	/**
	 * 过滤后记录数
	 */
	private int recordsFiltered;
	
	/**
	 * 数据列表
	 */
	private Object data;

	
	public Result() {
		this.success = true;
		this.message = "";
		this.draw = 0;
		this.recordsTotal = 0;
		this.recordsFiltered = 0;
		this.data = null;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
