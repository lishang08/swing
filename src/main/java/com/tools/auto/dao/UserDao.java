package com.tools.auto.dao;

import org.springframework.context.ApplicationContext;

import com.tools.auto.model.User;

/**
* Author: fulishang
* Create Time  : 2017年5月13日,下午3:57:33
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public interface UserDao {

	/**
	 * 根据用户的名称及密码验证用户是否存在,返回用户
	 * @param name
	 * @param password
	 * @return
	 */
	User checkLogin(ApplicationContext context, String name, String password);
}
