/**
 * Project:当家猫项目
 * Module ID：core
 * JDK Version Used:JDK1.8
 * Description:角色数据层接口
 * @author yjl
 * @version 1.0,2015/08/30
 */
package com.afeey.permission.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.afeey.permission.core.po.Role;

/**
 * 角色数据层接口
 * 
 * @author yjl
 *
 */
public interface IRoleDao {
	/**
	 * 新增角色
	 * 
	 * @param role
	 *            角色对象
	 * @return 影响的记录数
	 */
	int insert(Role role);

	/**
	 * 修改角色
	 * 
	 * @param role
	 *            角色对象
	 * @return 影响的记录数
	 */
	int update(Role role);

	/**
	 * 删除角色
	 * 
	 * @param id
	 *            角色id
	 * @return 影响的记录数
	 */
	int delete(String id);

	/**
	 * 查询角色
	 * 
	 * @param id
	 *            ID
	 * @return 角色对象
	 */
	Role selectById(String id);

	/**
	 * 查询角色
	 * 
	 * @param name
	 *            名称
	 * @return 角色对象
	 */
	Role selectByName(String name);

	/**
	 * 查询角色
	 * 
	 * @param code
	 *            代码
	 * @return 角色对象
	 */
	Role selectByCode(String code);

	/**
	 * 获取最大排序号
	 * 
	 * @return 最大排序号
	 */
	int selectMaxSort();
	
	/**
	 * 查询用户角色列表
	 * @param userId 用户ID
	 * @return 角色列表
	 */
	List<Role> selectByUserId(String userId);

	/**
	 * 根据参数键值对(名称/代码/状态) 查询角色列表
	 * 
	 * @param params
	 *            参数
	 * @return 角色集合
	 */
	List<Role> selectByParams(
			@Param(value = "params") Map<Object, Object> params);
}