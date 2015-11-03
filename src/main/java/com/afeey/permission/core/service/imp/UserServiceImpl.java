/**
 * Project:当家猫项目
 * Module ID：core
 * JDK Version Used:JDK1.8
 * Description:用户服务层接口实现类
 * @author 杨金龙
 * @version 1.0,2015/08/30
 */
package com.afeey.permission.core.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.afeey.permission.core.dao.IRoleDao;
import com.afeey.permission.core.dao.IUserDao;
import com.afeey.permission.core.dao.IUserRoleDao;
import com.afeey.permission.core.enums.UseStatusEnum;
import com.afeey.permission.core.exception.CellphoneExistException;
import com.afeey.permission.core.exception.EmailExistException;
import com.afeey.permission.core.exception.UserNameExistException;
import com.afeey.permission.core.po.Role;
import com.afeey.permission.core.po.User;
import com.afeey.permission.core.po.UserRole;
import com.afeey.permission.core.service.IPage;
import com.afeey.permission.core.service.PageInfoAdapter;
import com.afeey.permission.core.service.IUserService;
import com.afeey.permission.core.util.StringUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 用户服务层接口实现类
 * 
 * @author 杨金龙
 * 
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IUserRoleDao userRoleDao;
	@Autowired
	private IRoleDao roleDao;

	@Override
	@Transactional
	public String add(User user, List<String> roleIds)
			throws UserNameExistException, EmailExistException,
			CellphoneExistException {
		// 验证用户名是否已存在
		if (null != userDao.selectByUserName(user.getUserName())) {
			throw new UserNameExistException("username exist");
		}

		// 验证手机号是否已存在
		if (null != userDao.selectByCellphone(user.getCellphone())) {
			throw new CellphoneExistException("cellphone exist");
		}

		// 验证邮箱是否已存在
		if (!StringUtil.isBlank(user.getMail()) && null != userDao.selectByMail(user.getMail())){
			throw new EmailExistException("mail exist");
		}

		// 保存用户
		int result = userDao.insert(user);

		// 保存角色
		if (!CollectionUtils.isEmpty(roleIds)) {
			for (String roleId : roleIds) {
				UserRole userRole = new UserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(roleId);
				userRoleDao.insert(userRole);
			}
		}
		return result == 1 ? user.getId() : null;
	}

	@Override
	public void update(User user) throws UserNameExistException,
			EmailExistException, CellphoneExistException {
		// 验证用户名是否已存在
		User selUser = userDao.selectByUserName(user.getUserName());
		if (null != selUser && !selUser.getId().equals(user.getId())) {
			throw new UserNameExistException("username exist");
		}

		// 验证手机号是否已存在
		selUser = userDao.selectByCellphone(user.getCellphone());
		if (null != selUser && !selUser.getId().equals(user.getId())) {
			throw new CellphoneExistException("cellphone exist");
		}

		// 验证邮箱是否已存在
		selUser = userDao.selectByMail(user.getMail());
		if (null != selUser && !selUser.getId().equals(user.getId())) {
			throw new EmailExistException("mail exist");
		}
		userDao.update(user);
	}

	@Override
	@Transactional
	public void delete(String id) {

		// 删除用户角色
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("userId", id);
		userRoleDao.deleteByParams(params);

		// 删除用户
		userDao.delete(id);
	}

	@Override
	public boolean existUserName(String userName) {
		return userDao.selectByUserName(userName) != null;
	}

	@Override
	public boolean existCellphone(String cellphone) {
		return userDao.selectByCellphone(cellphone) != null;
	}

	@Override
	public boolean existEmail(String email) {
		return userDao.selectByMail(email) != null;
	}

	@Override
	public User getById(String id) {
		return userDao.selectById(id);
	}

	@Override
	public User getByUserName(String userName) {
		return userDao.selectByUserName(userName);
	}

	@Override
	public User getByCellphone(String cellphone) {
		return userDao.selectByCellphone(cellphone);
	}

	@Override
	public User getByMail(String mail) {
		return userDao.selectByMail(mail);
	}

	@Override
	public List<User> list(String roleId, String userName, String cellphone,
			String email, UseStatusEnum statusEnum) {
		Map<Object, Object> params = new HashMap<Object, Object>();
		if (StringUtil.isNotBlank(roleId)) {
			params.put("roleId", roleId);
		}
		if (StringUtil.isNotBlank(userName)) {
			params.put("userName", userName);
		}
		if (StringUtil.isNotBlank(cellphone)) {
			params.put("cellphone", cellphone);
		}
		if (StringUtil.isNotBlank(email)) {
			params.put("mail", email);
		}
		if (statusEnum != null) {
			params.put("status", statusEnum);
		}

		return userDao.selectByParams(params);
	}

	@Override
	public IPage<User> list(String roleId, String userName, String cellphone,
			String email, UseStatusEnum statusEnum, int number, int size) {
		PageHelper.startPage(number, size);
		Map<Object, Object> params = new HashMap<Object, Object>();
		if (StringUtil.isNotBlank(roleId)) {
			params.put("roleId", roleId);
		}
		if (StringUtil.isNotBlank(userName)) {
			params.put("userName", userName);
		}
		if (StringUtil.isNotBlank(cellphone)) {
			params.put("cellphone", cellphone);
		}
		if (StringUtil.isNotBlank(email)) {
			params.put("mail", email);
		}
		if (statusEnum != null) {
			params.put("status", statusEnum);
		}

		List<User> list = userDao.selectByParams(params);
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		IPage<User> page = new PageInfoAdapter<User>(pageInfo);

		return page;
	}

	@Override
	public List<String> listPermissions(String userId) {
		List<String> permissionList = new ArrayList<String>();

		// 获取用户角色列表
		List<Role> roleList = roleDao.selectByUserId(userId);

		// 计算出用户权限列表
		for (Role role : roleList) {
			if (role.getPermissionList().isEmpty()) {
				continue;
			}
			for (String permission : role.getPermissionList()) {
				if (!permissionList.contains(permission)) {
					permissionList.add(permission);
				}
			}
		}
		return permissionList;
	}

	@Override
	public List<Role> listRoles(String userId) {
		return roleDao.selectByUserId(userId);
	}

	@Override
	@Transactional
	public void updateRoles(String userId, List<String> roleIds) {

		// 删除用户角色列表
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("userId", userId);
		userRoleDao.deleteByParams(map);

		// 保存用户角色列表
		for (String roleId : roleIds) {
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			userRoleDao.insert(userRole);
		}
	}
}