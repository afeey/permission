package com.afeey.permission.core.service;

import java.util.List;

import com.afeey.permission.core.enums.ResourceTypeEnum;
import com.afeey.permission.core.enums.UseStatusEnum;
import com.afeey.permission.core.po.Resource;
import com.afeey.permission.core.service.IPage;

/**
 * 资源服务层接口
 * 
 * @author 杨金龙
 *
 */
public interface IResourceService {
	
	/** 
	 * 添加资源
	 * @param Resource 资源对象
	 * @return ID
	 */
	String add(Resource Resource);

	/**
	 * 修改资源
	 * @param Resource 资源
	 */
	void update(Resource Resource);

	/**
	 * 删除资源
	 * @param id ID
	 */
	void delete(String id);
	
	/** 
	 * 删除资源
	 * @param ids ID集合
	 */
	void delete(List<String> ids);

	/**
	 * 获取资源
	 * @param id 资源id
	 * @return 资源对象
	 */
	Resource getById(String id);
	
	/**
	 * 获取下级资源列表（直接下级）
	 * @param id 资源ID
	 * @param name 名称（模糊匹配，可为null或""）
	 * @param url Url（模糊匹配，可为null或""）
	 * @param type 类型（可为null）
	 * @param status 状态(可为null)
	 * @return 资源列表
	 */
	List<Resource> children(String id, String name, String url, ResourceTypeEnum type,UseStatusEnum status);

	/**
	 * 获取下级资源列表（直接下级）
	 * @param id 资源ID
	 * @param name 名称（模糊匹配，可为null或""）
	 * @param url Url（模糊匹配，可为null或""）
	 * @param type 类型（可为null）
	 * @param status 状态(可为null)
	 * @param number 当前页码
	 * @param size 每页记录数
	 * @return 资源列表
	 */
	IPage<Resource> children(String id, String name, String url, ResourceTypeEnum type,UseStatusEnum status,int number, int size);

	/**
	 * 查询资源列表
	 * @param name 名称（模糊匹配，可为null或""）
	 * @param url Url（模糊匹配，可为null或""）
	 * @param type 类型（可为null）
	 * @param status 状态(可为null)
	 * @return 资源列表
	 */
	List<Resource> list(String name, String url, ResourceTypeEnum type,UseStatusEnum status);

	/**
	 * 查询资源列表
	 * @param name 名称（模糊匹配，可为null或""）
	 * @param url Url（模糊匹配，可为null或""）
	 * @param type 类型（可为null）
	 * @param status 状态(可为null)
	 * @param number 当前页码
	 * @param size 每页记录数
	 * @return 资源列表
	 */
	IPage<Resource> list(String name, String url, ResourceTypeEnum type,UseStatusEnum status, int number, int size);

	/**
	 * 查询资源根列表
	 * @param status 状态(可为null)
	 * @return 资源列表
	 */
	List<Resource> listRoot(UseStatusEnum status);
}