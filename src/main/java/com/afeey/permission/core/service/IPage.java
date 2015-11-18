package com.afeey.permission.core.service;

import java.util.List;

/**
 * 数据分页信息接口
 * 
 * @author wyf
 *
 * @param <T> 类型
 */
public interface IPage<T> {
	
	/**
	 * 获取当前页码
	 * @return 当前页码
	 */
	int getPageNumber();
	
	/**
	 * 获取每页记录数
	 * @return 每页记录数
	 */
	int getPageSize();
	
	/**
	 * 获取总记录数
	 * @return 总记录数
	 */
	int getTotal();
	
	/**
	 * 获取总页数
	 * @return 总页数
	 */
	int getPages();
	
	/**
	 * 获取数据列表
	 * @return 数据列表
	 */
	List<T> getList();
}
