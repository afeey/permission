package com.afeey.permission.core.service;

import java.util.List;

import com.afeey.permission.core.enums.UseStatusEnum;
import com.afeey.permission.core.exception.CellphoneExistException;
import com.afeey.permission.core.exception.EmailExistException;
import com.afeey.permission.core.exception.UserNameExistException;
import com.afeey.permission.core.po.Role;
import com.afeey.permission.core.po.User;
import com.afeey.permission.core.service.IPage;

/**
 * 用户服务层接口
 * 
 * @author yjl
 *
 */
public interface IUserService {
	/**
	 * 添加用户
	 * 
	 * @param user
	 *            用户对象
	 * @param roleIds
	 *            角色ID集合
	 * @return uuid 主键
	 * @throws UserNameExistException
	 *             用户名已存在异常
	 * @throws EmailExistException
	 *             邮箱已存在异常
	 * @throws CellphoneExistException
	 *             手机号已存在异常
	 */
	String add(User user, List<String> roleIds) throws UserNameExistException,
			EmailExistException, CellphoneExistException;

	/**
	 * 修改用户
	 * 
	 * @param user
	 *            用户对象
	 * @throws UserNameExistException
	 *             用户名已存在异常
	 * @throws EmailExistException
	 *             邮箱已存在异常
	 * @throws CellphoneExistException
	 *             手机号已存在异常
	 */
	void update(User user) throws UserNameExistException, EmailExistException,
			CellphoneExistException;

	/**
	 * 删除用户(同时删除用户与角色的关联)
	 * 
	 * @param id
	 *            用户id
	 */
	void delete(String id);

	/**
	 * 用户名是否存在
	 * 
	 * @param userName
	 *            用户名
	 * @return true存在 false不存在
	 */
	boolean existUserName(String userName);

	/**
	 * 手机是否存在
	 * 
	 * @param cellphone
	 *            手机
	 * @return true存在 false不存在
	 */
	boolean existCellphone(String cellphone);

	/**
	 * 邮箱是否存在
	 * 
	 * @param email
	 *            邮箱
	 * @return true存在 false不存在
	 */
	boolean existEmail(String email);

	/**
	 * 获取用户
	 * 
	 * @param id
	 *            用户id
	 * @return 用户对象
	 */
	User getById(String id);

	/**
	 * 根据用户名获取用户
	 * 
	 * @param userName
	 *            用户名
	 * @return 用户对象
	 */
	User getByUserName(String userName);

	/**
	 * 根据手机号获取用户
	 * 
	 * @param cellphone
	 *            手机号
	 * @return 用户对象
	 */
	User getByCellphone(String cellphone);

	/**
	 * 根据邮箱获取用户
	 * 
	 * @param mail
	 *            邮箱
	 * @return 用户对象
	 */
	User getByMail(String mail);

	/**
	 * 查询用户列表
	 * 
	 * @param roleId
	 *            角色Id(可为null)
	 * @param userName
	 *            用户名(模糊查询，可为null)
	 * @param cellphone
	 *            手机号(模糊查询，可为null)
	 * @param email
	 *            邮箱(模糊查询，可为null)
	 * @param statusEnum
	 *            状态(可为null)
	 * @return 用户列表(根据用户名升序排列)
	 */
	List<User> list(String roleId, String userName, String cellphone,
			String email, UseStatusEnum statusEnum);

	/**
	 * 分页查询用户列表
	 * 
	 * @param roleId
	 *            角色Id(可为null)
	 * @param userName
	 *            用户名(模糊查询，可为null)
	 * @param cellphone
	 *            手机号(模糊查询，可为null)
	 * @param email
	 *            邮箱(模糊查询，可为null)
	 * @param statusEnum
	 *            状态(可为null)
	 * @param number
	 *            当前页码
	 * @param size
	 *            每页记录数
	 * @return 用户列表(根据用户名升序排列)
	 */
	IPage<User> list(String roleId, String userName, String cellphone,
			String email, UseStatusEnum statusEnum, int number, int size);
	
	/**
	 * 查询用户的权限列表
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	List<String> listPermissions(String userId);
	
	/**
	 * 查询用户角色列表
	 * @param userId 用户ID
	 * @return 角色列表
	 */
	List<Role> listRoles(String userId);
	
	/**
	 * 更新用户角色列表
	 * @param userId 用户ID
	 * @param roleIds 角色ID集合
	 */
	void updateRoles(String userId,List<String> roleIds);
}