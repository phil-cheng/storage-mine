package com.cf.storage.common.initvars;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件内容
 * @author phill
 *
 */
@Component("appVars")
public class AppVars {
	
//	@PostConstruct
//	public void init() {
//		
//	}
	@Value("${imageFilePath}")
	public String imageFilePath;
	
}
