package com.cf.storage.core.user.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.cf.storage.core.user.dao.UserViewMapper;
import com.cf.storage.core.user.po.UserView;

@Service
public class UserServiceImpl implements UserService {
   
	@Resource
    UserViewMapper userViewMapper;

	@Override
	public boolean existUser(String email) {
		UserView uv = userViewMapper.getUserByEmail(email);
		if(uv != null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Integer saveUser(UserView uv) {
		return userViewMapper.insertSelective(uv);
	}

	@Override
	public boolean checkLogin(UserView uv) {
		UserView user = userViewMapper.selectOne(uv);
		if(user != null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public UserView selectUser(UserView uv) throws Exception {
		return userViewMapper.selectOne(uv);
	}

}
