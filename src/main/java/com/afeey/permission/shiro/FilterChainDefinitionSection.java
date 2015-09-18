package com.afeey.permission.shiro;

import java.text.MessageFormat;
import java.util.Set;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * 过滤链定义Section
 * 
 * @author Administrator
 *
 */
public class FilterChainDefinitionSection implements FactoryBean<Section> {

	private static final Logger log = LoggerFactory
			.getLogger(FilterChainDefinitionSection.class);

	// @Autowired
	// private ResourceDao resourceDao;

	private String filterChainDefinitions;

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

		// 加载权限
		section.put("/admin/permission",
				MessageFormat.format(PREMISSION_STRING, "permission"));
		section.put("/admin/resource",
				MessageFormat.format(PREMISSION_STRING, "resource"));
		section.put("/admin/role",
				MessageFormat.format(PREMISSION_STRING, "role"));
		section.put("/admin/user",
				MessageFormat.format(PREMISSION_STRING, "user"));
		section.put("/admin/region",
				MessageFormat.format(PREMISSION_STRING, "region"));

		if (log.isDebugEnabled()) {
			log.debug(MessageFormat.format(PREMISSION_STRING, "permission"));

			log.debug("get permission");
			
			Set<String> keySet=section.keySet();
			for(String key : keySet){
				//log.debug("key: {}, value: {}",section.get(key),section.);   
			}
			for (int i=0;i<keySet.size();i++) {
				
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
