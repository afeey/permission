package com.afeey.permission.core.service;

import java.util.List;

import com.afeey.permission.core.enums.UseStatusEnum;
import com.afeey.permission.core.exception.CodeExistException;
import com.afeey.permission.core.exception.NameExistException;
import com.afeey.permission.core.po.Resource;
import com.afeey.permission.core.po.Role;
import com.afeey.permission.core.service.IPage;

/**
 * 角色服务层接口
 * 
 * @author 王跃飞
 *
 */
public interface IRoleService {
	/**
	 * 添加角色
	 * @param role 角色对象
	 * @return uuid 主键
	 * @throws NameExistException 名称已存在异常
	 * @throws CodeExistException 代码已存在异常
	 */
	String add(Role role) throws NameExistException, CodeExistException;

	/**
	 * 修改角色(不能修改排序)
	 * @param role 角色对象
	 * @throws NameExistException 名称已存在异常
	 * @throws CodeExistException 代码已存在异常
	 */
	void update(Role role) throws NameExistException, CodeExistException;

	/**
	 * 删除角色
	 * @param id 角色id
	 */
	void delete(String id);

	/**
	 * 移动到目标角色前
	 * @param targetId 目标Id
	 * @param moveId 移动Id
	 */
	void moveBefore(String targetId, String moveId);
	
	/**
	 * 移动到目标角色后
	 * @param targetId 目标Id
	 * @param moveId 移动Id
	 */
	void moveAfter(String targetId, String moveId);
	
	/**
	 * 名称是否存在
	 * @param name 名称
	 * @return true存在 false不存在
	 */
	boolean existName(String name);
	
	/**
	 * 代码是否存在
	 * @param code 代码
	 * @return true存在 false不存在
	 */
	boolean existCode(String code);

	/**
	 * 获取角色
	 * @param id 角色id
	 * @return 角色对象
	 */
	Role getById(String id);

	/**
	 * 根据代码获取角色
	 * @param code 代码
	 * @return 角色对象
	 */
	Role getByCode(String code);

	/**
	 * 查询角色列表
	 * @param name 名称（可为null）
	 * @param code 代码（可为null）
	 * @param status 状态枚举(可为null)
	 * @return 角色列表
	 */
	List<Role> list(String name, String code, UseStatusEnum status);

	/**
	 * 查询角色列表
	 * @param name 名称（可为null）
	 * @param code 代码（可为null）
	 * @param status 状态枚举(可为null)
	 * @param number 当前页码
	 * @param size 每页记录数
	 * @return 角色列表
	 */
	IPage<Role> list(String name, String code, UseStatusEnum status, int number, int size);
	
	/**
	 * 查询角色权限列表
	 * @param roleId 角色ID
	 * @return 权限列表
	 */
	List<String> listPermissions(String roleId);
	
	/**
	 * 查询角色资源列表
	 * @param roleId 角色ID
	 * @return 资源列表
	 */
	List<Resource> listResources(String roleId);
	
	/**
	 * 更新角色资源列表
	 * @param roleId 角色Id
	 * @param resourceIds 资源ID列表
	 */
	void updateResources(String roleId,List<String> resourceIds);
}