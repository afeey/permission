package com.afeey.permission.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试类基类，默认不使用事务回滚
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring-mvc.xml"})
public class MvcBaseTest extends AbstractJUnit4SpringContextTests {
}
