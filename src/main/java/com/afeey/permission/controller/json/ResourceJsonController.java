package com.afeey.permission.controller.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.afeey.permission.core.po.Resource;
import com.afeey.permission.core.service.IResourceService;

/**
 * 资源控制器
 * 
 * @author wyf
 *
 */
@Controller
@ResponseBody
@RequestMapping("/json/resource")
public class ResourceJsonController {

	@Autowired
	private IResourceService resourceService;
	

	@RequestMapping(value = "/list")
	public Object list(){
		
		boolean success = false;
		String msg = "";
		
		List<Resource> list = resourceService.list(null, null, null, null);

		JsonResult result=new JsonResult();
		result.setCode(success ? "200" : "401");
		result.setMessage(msg);
		result.setData(list);
		
		return result;
	}
	
	
}
