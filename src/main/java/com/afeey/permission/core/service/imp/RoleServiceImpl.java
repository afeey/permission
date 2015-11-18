/**
 * Project:当家猫项目
 * Module ID：core
 * JDK Version Used:JDK1.8
 * Description:角色服务层接口实现类
 * @author yjl
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

import com.afeey.permission.core.dao.IResourceDao;
import com.afeey.permission.core.dao.IRoleDao;
import com.afeey.permission.core.dao.IRoleResourceDao;
import com.afeey.permission.core.dao.IUserRoleDao;
import com.afeey.permission.core.enums.UseStatusEnum;
import com.afeey.permission.core.exception.CodeExistException;
import com.afeey.permission.core.exception.NameExistException;
import com.afeey.permission.core.po.Resource;
import com.afeey.permission.core.po.Role;
import com.afeey.permission.core.po.RoleResource;
import com.afeey.permission.core.service.IPage;
import com.afeey.permission.core.service.PageInfoAdapter;
import com.afeey.permission.core.service.IRoleService;
import com.afeey.permission.core.util.StringUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 角色服务层接口实现类
 * 
 * @author yjl
 * 
 */
@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IResourceDao resourceDao;

	@Autowired
	private IUserRoleDao userRoleDao;

	@Autowired
	private IRoleResourceDao roleResourceDao;

	@Override
	public String add(Role role) throws NameExistException, CodeExistException {

		// 检测名称是否存在
		if (null != roleDao.selectByName(role.getName())) {
			throw new NameExistException("name exist");
		}
		// 检测代码是否存在
		if (null != roleDao.selectByCode(role.getCode())) {
			throw new CodeExistException("code exist");
		}
		// 获取排序
		role.setSort(roleDao.selectMaxSort() + 1);
		int result = roleDao.insert(role);

		return result == 1 ? role.getId() : null;
	}

	@Override
	public void update(Role role) throws NameExistException, CodeExistException {
		// 检测名称是否存在
		Role selRole = roleDao.selectByName(role.getName());
		if (null != selRole && !selRole.getId().equals(role.getId())) {
			throw new NameExistException("name exist");
		}
		// 检测代码是否存在
		selRole = roleDao.selectByCode(role.getCode());
		if (null != selRole && !selRole.getId().equals(role.getId())) {
			throw new CodeExistException("code exist");
		}

		selRole = roleDao.selectById(role.getId());
		role.setSort(selRole.getSort());
		roleDao.update(role);
	}

	@Transactional
	@Override
	public void delete(String id) {
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("roleId", id);
		userRoleDao.deleteByParams(params);
		roleResourceDao.deleteByParams(params);

		roleDao.delete(id);
	}

	@Transactional
	@Override
	public void moveBefore(String targetId, String moveId) {

	}

	@Transactional
	@Override
	public void moveAfter(String targetId, String moveId) {

	}

	@Override
	public boolean existName(String name) {
		return roleDao.selectByName(name) != null;
	}

	@Override
	public boolean existCode(String code) {
		return roleDao.selectByCode(code) != null;
	}

	@Override
	public Role getById(String id) {
		return roleDao.selectById(id);
	}

	@Override
	public Role getByCode(String code) {
		return roleDao.selectByCode(code);
	}

	@Override
	public List<Role> list(String name, String code, UseStatusEnum statusEnum) {
		Map<Object, Object> params = new HashMap<Object, Object>();
		if (StringUtil.isNotBlank(name)) {
			params.put("name", name);
		}
		if (StringUtil.isNotBlank(code)) {
			params.put("code", code);
		}
		if (statusEnum != null) {
			params.put("status", statusEnum);
		}
		return roleDao.selectByParams(params);
	}

	@Override
	public IPage<Role> list(String name, String code, UseStatusEnum statusEnum,
			int number, int size) {

		Map<Object, Object> params = new HashMap<Object, Object>();
		if (StringUtil.isNotBlank(name)) {
			params.put("name", name);
		}
		if (StringUtil.isNotBlank(code)) {
			params.put("code", code);
		}
		if (statusEnum != null) {
			params.put("status", statusEnum);
		}

		PageHelper.startPage(number, size);
		List<Role> list = roleDao.selectByParams(params);
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		IPage<Role> page = new PageInfoAdapter<Role>(pageInfo);

		return page;
	}

	@Override
	public List<String> listPermissions(String roleId) {
		List<String> permissionList = new ArrayList<String>();
		
		Role role = roleDao.selectById(roleId);
		if (null == role) {
			return permissionList;
		}
		//先从权限缓存字段获取权限,获取权限为空列表，查询权限关联表获取
		permissionList=role.getPermissionList();
		if(permissionList.isEmpty()){
			permissionList=getRolePermissions(roleId);
		}
		return permissionList;
	}

	@Override
	public List<Resource> listResources(String roleId) {
		return resourceDao.selectByRoleId(roleId);
	}

	@Override
	@Transactional
	public void updateResources(String roleId, List<String> resourceIds) {

		// 删除关联
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("roleId", roleId);
		roleResourceDao.deleteByParams(map);

		// 新增关联
		for (String resourceId : resourceIds) {
			RoleResource roleResource = new RoleResource();
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(resourceId);

			roleResourceDao.insert(roleResource);
		}
		//获取权限列表
		List<String> permissionList = getRolePermissions(roleId);

		// 更新角色权限
		Role role = roleDao.selectById(roleId);
		role.setPermissionList(permissionList);
		roleDao.update(role);
	}
	
	/**
	 * 查询出角色权限列表
	 * @param roleId 角色ID
	 * @return 权限列表
	 */
	private List<String> getRolePermissions(String roleId){
		List<String> permissionList = new ArrayList<String>();
		
		List<Resource> resourceList = resourceDao.selectByRoleId(roleId);
		for (Resource resource : resourceList) {
			if (resource.getPermissionList().isEmpty()) {
				continue;
			}
			for (String permission : resource.getPermissionList()) {
				if (!permissionList.contains(permission)) {
					permissionList.add(permission);
				}
			}
		}
		return permissionList;
	}
}