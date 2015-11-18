package com.afeey.permission.controller.json;

/**
 * 列表结果
 * 
 * @author wyf
 *
 */
public class ListResult {
	
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

	
	public ListResult() {
		this.draw = 0;
		this.recordsTotal = 0;
		this.recordsFiltered = 0;
		this.data = null;
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
