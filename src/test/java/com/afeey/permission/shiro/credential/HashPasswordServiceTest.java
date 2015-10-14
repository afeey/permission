package com.afeey.permission.shiro.credential;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring-mybatis.xml","classpath:config/spring-shiro.xml"})
public class HashPasswordServiceTest {

	@Autowired
	private HashPasswordService hashPasswordService;
	
	@Test
	public void testHashPassword() {
		String password = hashPasswordService.hashPassword("123456");
		System.out.println(password);
		
		password = hashPasswordService.hashPassword("abc");
		System.out.println(password);
	}

}
