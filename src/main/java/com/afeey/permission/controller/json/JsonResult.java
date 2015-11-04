package com.afeey.permission.controller.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json结果对象
 * 
 * @author 王跃飞
 *
 */
public class JsonResult {

	private static final Logger log = LoggerFactory.getLogger(JsonResult.class);

	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 返回提示
	 */
	private String message;

	/**
	 * 跳转链接
	 */
	private String redirect;

	/**
	 * 返回数据
	 */
	private Object data;

	public JsonResult() {
		this.code = "200";
		this.message = "";
		this.redirect = "";
		this.data = null;
	}

	public String toString() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String json = "";
		try {
			json=objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.debug(e.getMessage());
		}
		return json;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
