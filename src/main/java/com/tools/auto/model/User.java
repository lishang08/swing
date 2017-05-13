package com.tools.auto.model;

import java.io.Serializable;

/**
* Author: fulishang
* Create Time  : 2017年5月13日,下午3:58:01
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
}
