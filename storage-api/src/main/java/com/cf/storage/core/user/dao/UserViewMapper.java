package com.cf.storage.core.user.dao;


import com.cf.storage.core.user.po.UserView;

import tk.mybatis.mapper.common.Mapper;

public interface UserViewMapper extends Mapper<UserView>{

	UserView getUserByEmail(String email);
}