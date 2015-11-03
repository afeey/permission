/**
 * Project:当家猫项目
 * Module ID：core
 * JDK Version Used:JDK1.8
 * Description:角色资源关联数据层接口
 * @author 杨金龙
 * @version 1.0,2015/08/30
 */
package com.afeey.permission.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.afeey.permission.core.po.RoleResource;


/**
 * 角色资源关联数据层接口
 * @author 杨金龙
 *
 */
public interface IRoleResourceDao {
	
	/**
	 * 新增角色资源
	 * @param roleResource 角色资源对象
	 * @return 影响的记录数
	 */
	int insert(RoleResource roleResource);
	
	/**
	 * 根据参数键值对删除角色资源列表
	 * @param params 参数<br>
	 * <p>可选键：
	 * roleId:String 角色ID<br>
	 * resourceId:String 资源ID
     * @return 影响行数
	 */
	int deleteByParams(@Param(value = "params") Map<Object,Object> params);

	/**
	 * 查询角色资源
	 * @param id ID
	 * @return 角色资源对象
	 */
	RoleResource selectById(String id);
	
	/**
	 * 根据参数键值对(角色Id/资源Id) 查询角色资源列表
	 * @param params 参数
	 * <p>可选键：<br>
	 * roleId:String 角色ID<br>
	 * resourceId:String 资源ID
     * @return 角色资源集合
	 */
	List<RoleResource> selectByParams(@Param(value = "params") Map<Object,Object> params);

	
}