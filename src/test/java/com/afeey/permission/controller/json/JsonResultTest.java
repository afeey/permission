package com.afeey.permission.controller.json;

import static org.junit.Assert.*;

import org.junit.Test;

public class JsonResultTest {

	@Test
	public void testToString() {
		JsonResult result=new JsonResult();
		result.setCode("200");
		result.setMessage("执行成功");
		
		assertTrue(result.toString().length()>0);
		System.out.println(result.toString());
		
	}

}
