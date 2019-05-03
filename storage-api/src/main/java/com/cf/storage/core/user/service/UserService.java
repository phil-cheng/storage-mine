package com.cf.storage.core.user.service;

import com.cf.storage.core.user.po.UserView;

public interface UserService {
	
	// 判断用户是否存在
	boolean existUser(String email);
	
	// 创建用户
	Integer saveUser(UserView uv);
	
	// 用户登陆验证
	boolean checkLogin(UserView uv);

	// 查询用户
	UserView selectUser(UserView uv) throws Exception;
}
