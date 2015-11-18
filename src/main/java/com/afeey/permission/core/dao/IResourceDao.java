package com.afeey.permission.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.afeey.permission.core.po.Resource;

/**
 * 资源数据层接口
 * 
 * @author yjl
 *
 */
public interface IResourceDao {
	/**
	 * 新增资源
	 * 
	 * @param resource
	 *            资源对象
	 * @return 影响的记录数
	 */
	int insert(Resource resource);

	/**
	 * 修改资源
	 * 
	 * @param resource
	 *            资源对象
	 * @return 影响的记录数
	 */
	int update(Resource resource);

	/**
	 * 删除资源
	 * 
	 * @param id
	 *            资源id
	 * @return 影响的记录数
	 */
	int delete(String id);

	/**
	 * 查询资源
	 * 
	 * @param id
	 *            ID
	 * @return 资源对象
	 */
	Resource selectById(String id);
	
	/**
	 * 根据角色ID查询资源列表
	 * 
	 * @param roleId
	 *            角色Id
	 * @return 资源列表
	 */
	List<Resource> selectByRoleId(String roleId);

	/**
	 * 根据参数键值对查询资源列表
	 * 
	 * @param params 
	 * 				参数<br>
	 * <p>可选参数:<br>
	 * parentId 父ID<br>
	 * name 名称（模糊查询）<br>
	 * type 类型<br>
	 * url Url（模糊查询）<br>
	 * status 状态<br>
	 * @return 资源集合
	 */
	List<Resource> selectByParams(
			@Param(value = "params") Map<Object, Object> params);

}