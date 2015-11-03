package com.afeey.permission.shiro.config;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.afeey.permission.core.po.Resource;
import com.afeey.permission.core.service.IResourceService;

/**
 * 过滤链定义Section
 * 
 * @author 王跃飞
 *
 */
public class FilterChainDefinitionSection implements FactoryBean<Section> {

	private static final Logger log = LoggerFactory
			.getLogger(FilterChainDefinitionSection.class);

	private String filterChainDefinitions;

	@Autowired
	private IResourceService resourceService;

	/**
	 * 默认权限字格式化符串
	 */
	public static final String PREMISSION_STRING = "perms[\"{0}\"]";

	@Override
	public Section getObject() throws Exception {

		// 加载默认urls
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);

		// 加载动态urls
		List<Resource> resourceList = resourceService.list(null, null, null, null);
		for (Resource resource : resourceList) {
			if (!resource.getPermissionList().isEmpty()) {
				String permissions = "";
				for (String permission : resource.getPermissionList()) {
					permissions += "," + permission;
				}
				permissions = permissions.substring(1);
				section.put(resource.getUrl(),
						MessageFormat.format(PREMISSION_STRING, permissions));
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("load urls:");

			Set<String> keySet = section.keySet();
			for (String key : keySet) {
				log.debug("{} = {}", key, section.get(key));
			}
		}
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

}
