package com.fst.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
 * <p>类名称     ：com.fst.common.BaseTest</p>
 * <p>描述          ：基础测试类，所有的测试都需要继承该类</p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年9月26日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class BaseTest {
	@Test
	public void test(){
		
	}
}
