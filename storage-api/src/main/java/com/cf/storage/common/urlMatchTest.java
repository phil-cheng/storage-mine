package com.cf.storage.common;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class urlMatchTest {

	
	/**
	 * -----------------
	 * ？匹配一个字符
	 *  匹配0个或多个字符
	 ** 匹配0个或多个目录
	 *---------------------
	 */
	
	public static void main(String[] args) {
		PathMatcher matcher = new AntPathMatcher();
		System.out.println(matcher.match("core/user/**", "core/user/toInnerUserView/a"));
		System.out.println(matcher.match("core/user/*", "core/user/toInnerUserView/a"));
		System.out.println(matcher.match("core/user?", "core/usera"));
	}

}
