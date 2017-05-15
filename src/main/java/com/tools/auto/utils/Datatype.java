package com.tools.auto.utils;
/**
* Author: fulishang
* Create Time  : 2017年5月15日,下午11:08:56
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public enum Datatype {

	STRING("VARCHAR2"), DATE("DATE"), NUMBER("NUMBER");
	
	private String type;
	
	private Datatype(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
