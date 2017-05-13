package com.tools.auto.service;

import org.springframework.context.ApplicationContext;

import com.tools.auto.dao.UserDao;
import com.tools.auto.model.User;

/**
* Author: fulishang
* Create Time  : 2017年5月13日,下午10:11:49
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	/** 用户登录service实现*/
	public User login(ApplicationContext context, String username,  String password) {
		userDao = (UserDao) context.getBean("userDao");
		User u = userDao.checkLogin(context, username, password);
		return u;
	}

}
