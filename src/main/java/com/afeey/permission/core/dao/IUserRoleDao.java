package com.afeey.permission.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.afeey.permission.core.po.UserRole;

/**
 * 用户角色关联数据层接口
 * @author 杨金龙
 *
 */
public interface IUserRoleDao {
	/**
	 * 新增用户角色关联
	 * @param userRole 用户角色关联对象
	 * @return 影响的记录数
	 */
	int insert(UserRole userRole);
	
	/**
	 * 根据参数键值对删除用户角色关联列表
	 * @param params 参数
	 * <p>可选键:<br>
	 * id:String 关联ID<br>
	 * userId:String 用户ID<br>
	 * roleId:String 角色ID
	 * @return 影响行数
	 */
	int deleteByParams(@Param(value = "params") Map<Object,Object> params);

	/**
	 * 查询用户角色关联
	 * @param id ID
	 * @return 用户角色关联对象
	 */
	UserRole selectById(String id);
	
	/**
	 * 根据参数键值对(用户Id/角色Id) 查询用户角色关联列表
	 * @param params 参数
     * @return 用户角色关联集合
	 */
	List<UserRole> selectByParams(@Param(value = "params") Map<Object,Object> params);
}