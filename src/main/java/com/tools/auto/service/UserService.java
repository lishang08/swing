package com.tools.auto.service;

import org.springframework.context.ApplicationContext;

import com.tools.auto.model.User;

/**
* Author: fulishang
* Create Time  : 2017年5月13日,下午10:09:10
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public interface UserService {
    /** 用户登录service */
	public User login(ApplicationContext context, String username, String password);
}
