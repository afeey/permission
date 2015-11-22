package com.afeey.permission.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.afeey.permission.core.po.Resource;
import com.afeey.permission.core.service.IResourceService;

/**
 * 资源API控制器
 * 
 * @author wyf
 *
 */
@Controller
@ResponseBody
@RequestMapping("/api/resource")
public class ResourceApiController {

	@Autowired
	private IResourceService resourceService;
	

	@RequestMapping(value = "/list")
	public Object list(){
		
		boolean success = false;
		String msg = "";
		
		List<Resource> list = resourceService.list(null, null, null, null);

		Result result=new Result();
		result.setSuccess(success);
		result.setMessage(msg);
		result.setData(list);
		
		return result;
	}
	
	
}
