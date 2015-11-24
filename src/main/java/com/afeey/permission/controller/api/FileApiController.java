package com.afeey.permission.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文件控制器
 * 
 * @author wyf
 *
 */
@Controller
@ResponseBody
@RequestMapping("/api/file/")
public class FileApiController {

	/**
	 * 文件上传
	 * @param request HttpServletRequest
	 * @return json
	 */
	@RequestMapping(value = "/upload")
	public Object upload(HttpServletRequest request){
		boolean success=false;
		String msg="";
		

		Result result = new Result();
		result.setSuccess(success);
		result.setMessage(msg);

		return result;
	}
}
