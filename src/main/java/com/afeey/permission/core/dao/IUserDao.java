/**
 * Project:当家猫项目
 * Module ID：core
 * JDK Version Used:JDK1.8
 * Description:用户数据层接口
 * @author yjl
 * @version 1.0,2015/08/30
 */
package com.afeey.permission.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.afeey.permission.core.po.User;

/**
 * 用户数据层接口
 * 
 * @author yjl
 *
 */
public interface IUserDao {
	/**
	 * 新增用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 影响的记录数
	 */
	int insert(User user);

	/**
	 * 修改用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 影响的记录数
	 */
	int update(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 *            用户id
	 * @return 影响的记录数
	 */
	int delete(String id);

	/**
	 * 查询用户
	 * 
	 * @param id
	 *            ID
	 * @return 用户对象
	 */
	User selectById(String id);
	
	/**
	 * 根据用户名查询用户
	 * @param userName 用户名
	 * @return 用户对象
	 */
	User selectByUserName(String userName);
	
	/**
	 * 根据手机号查询用户
	 * @param cellphone 手机号
	 * @return 用户对象
	 */
	User selectByCellphone(String cellphone);
	
	/**
	 * 根据邮箱查询用户
	 * @param mail 邮箱
	 * @return 用户对象
	 */
	User selectByMail(String mail);

	/**
	 * 根据参数键值对查询用户列表
	 * 
	 * @param params
	 *            参数
	 * <p>可选键：<br>
	 * roleId ：String 角色Id<br>
	 * userName ：String 用户名Id<br>
	 * cellphone ：String 手机号<br>
	 * mail ：String 邮箱<br>
	 * status ：UseStatusEnum 状态<br>
	 * @return 用户集合
	 */
	List<User> selectByParams(
			@Param(value = "params") Map<Object, Object> params);
}