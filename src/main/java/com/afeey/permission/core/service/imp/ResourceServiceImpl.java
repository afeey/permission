/**
 * Project:当家猫项目
 * Module ID：core
 * JDK Version Used:JDK1.8
 * Description:资源服务层接口实现类
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

import com.afeey.permission.core.dao.IResourceDao;
import com.afeey.permission.core.dao.IRoleDao;
import com.afeey.permission.core.dao.IRoleResourceDao;
import com.afeey.permission.core.enums.ResourceTypeEnum;
import com.afeey.permission.core.enums.UseStatusEnum;
import com.afeey.permission.core.po.Resource;
import com.afeey.permission.core.po.Role;
import com.afeey.permission.core.po.RoleResource;
import com.afeey.permission.core.service.IPage;
import com.afeey.permission.core.service.PageInfoAdapter;
import com.afeey.permission.core.service.IResourceService;
import com.afeey.permission.core.util.StringUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 资源服务层接口实现类
 * 
 * @author 杨金龙
 * 
 */
@Service
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private IResourceDao resourceDao;
	@Autowired
	private IRoleResourceDao roleResourceDao;
	@Autowired 
	private IRoleDao roleDao;

	@Override
	public String add(Resource resource) {
		int result = resourceDao.insert(resource);
		return result == 1 ? resource.getId() : null;
	}

	@Override
	@Transactional
	public void update(Resource resource) {
		// 更新资源
		resourceDao.update(resource);

		// 计算出关联的角色列表
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("resourceId", resource.getId());
		List<RoleResource> roleResources = roleResourceDao.selectByParams(map);
		List<String> roleIds=new ArrayList<String>();
		for (RoleResource roleResource : roleResources) {
			if(!roleIds.contains(roleResource.getRoleId())){
				roleIds.add(roleResource.getRoleId());
			}
		}
		
		// 更新关联角色的权限
		for (String roleId : roleIds) {
			// 计算出角色的权限
			List<Resource> resourceList = resourceDao.selectByRoleId(roleId);
			List<String> permissionList = new ArrayList<String>();
			for (Resource r : resourceList) {
				if (r.getPermissionList().isEmpty()) {
					continue;
				}
				for (String permission : r.getPermissionList()) {
					if (!permissionList.contains(permission)) {
						permissionList.add(permission);
					}
				}
			}

			// 更新角色权限
			Role role = roleDao.selectById(roleId);
			role.setPermissionList(permissionList);
			roleDao.update(role);
		}
	}

	@Override
	@Transactional
	public void delete(String id) {

		// 删除与角色关联
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("resourceId", id);
		roleResourceDao.deleteByParams(params);

		// 删除资源
		resourceDao.delete(id);
	}

	@Override
	@Transactional
	public void delete(List<String> ids) {
		for (String id : ids) {
			delete(id);
		}
	}

	@Override
	public Resource getById(String id) {
		return resourceDao.selectById(id);
	}

	@Override
	public List<Resource> children(String id, String name, String url,
			ResourceTypeEnum type, UseStatusEnum status) {

		return list(id, name, url, type, status);
	}

	@Override
	public IPage<Resource> children(String id, String name, String url,
			ResourceTypeEnum type, UseStatusEnum status, int number, int size) {

		return list(id, name, url, type, status, number, size);
	}

	@Override
	public List<Resource> list(String name, String url, ResourceTypeEnum type,
			UseStatusEnum status) {

		return list(null, name, url, type, status);
	}

	@Override
	public IPage<Resource> list(String name, String url, ResourceTypeEnum type,
			UseStatusEnum status, int number, int size) {
		return list(null, name, url, type, status, number, size);
	}

	@Override
	public List<Resource> listRoot(UseStatusEnum status) {

		// 根节点父ID为0
		return list("0", null, null, status);
	}

	/**
	 * 查询资源列表
	 * 
	 * @param parentId
	 *            父ID（可为null）
	 * @param name
	 *            名称（模糊查询,可为null）
	 * @param url
	 *            Url（模糊查询,可为null）
	 * @param type
	 *            类型(可为null)
	 * @param status
	 *            状态(可为null)
	 * @return 资源列表
	 */
	private List<Resource> list(String parentId, String name, String url,
			ResourceTypeEnum type, UseStatusEnum status) {

		Map<Object, Object> params = new HashMap<Object, Object>();
		if (StringUtil.isNotBlank(parentId)) {
			params.put("parentId", parentId);
		}
		if (StringUtil.isNotBlank(name)) {
			params.put("name", name);
		}
		if (StringUtil.isNotBlank(url)) {
			params.put("url", url);
		}
		if (type != null) {
			params.put("type", type);
		}
		if (status != null) {
			params.put("status", status);
		}
		return resourceDao.selectByParams(params);

	}

	/**
	 * 查询资源列表
	 * 
	 * @param parentId
	 *            父ID（可为null）
	 * @param name
	 *            名称（模糊查询,可为null）
	 * @param url
	 *            Url（模糊查询,可为null）
	 * @param type
	 *            类型(可为null)
	 * @param status
	 *            状态(可为null)
	 * @param number
	 *            当前页码
	 * @param size
	 *            每页记录数
	 * @return 资源列表
	 */
	private IPage<Resource> list(String parentId, String name, String url,
			ResourceTypeEnum type, UseStatusEnum status, int number, int size) {

		Map<Object, Object> params = new HashMap<Object, Object>();
		if (StringUtil.isNotBlank(parentId)) {
			params.put("parentId", parentId);
		}
		if (StringUtil.isNotBlank(name)) {
			params.put("name", name);
		}
		if (StringUtil.isNotBlank(url)) {
			params.put("url", url);
		}
		if (type != null) {
			params.put("type", type);
		}
		if (status != null) {
			params.put("status", status);
		}

		PageHelper.startPage(number, size);
		List<Resource> list = resourceDao.selectByParams(params);
		PageInfo<Resource> pageInfo = new PageInfo<Resource>(list);
		IPage<Resource> page = new PageInfoAdapter<Resource>(pageInfo);

		return page;

	}
}