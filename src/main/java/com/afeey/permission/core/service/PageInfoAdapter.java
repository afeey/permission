package com.afeey.permission.core.service;

import java.util.List;

import com.afeey.permission.core.service.IPage;
import com.github.pagehelper.PageInfo;

/**
 * 分页数据接口适配器类，PageInfo转换城IPage
 * 
 * @author wyf
 *
 * @param <T>
 *            类型
 */
public class PageInfoAdapter<T> implements IPage<T> {

	private PageInfo<T> pageInfo;

	public PageInfoAdapter(PageInfo<T> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public int getPageSize() {
		return pageInfo.getPageSize();
	}

	public int getPageNumber() {
		return pageInfo.getPageNum();
	}

	public int getTotal() {
		return (int) pageInfo.getTotal();
	}

	public int getPages() {
		return pageInfo.getPages();
	}

	public List<T> getList() {
		return pageInfo.getList();
	}

}
