package com.tools.auto.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
* Author: fulishang
* Create Time  : 2017年5月13日,下午10:35:22
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public class SpringDBInit {
	
	/**单例 */
	private static SpringDBInit instance = null;
	
	public SpringDBInit() {
		
	}
	
	/** 获取单例 */
	public static SpringDBInit getInstance() {
		if (instance == null) {
			synchronized (SpringDBInit.class) {
				if (instance == null) {
					instance = new SpringDBInit();
				}
			}
		}
		return instance;
	}
	
	/** 加载 spring applicationcontext
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ApplicationContext init () throws Exception{
		return new ClassPathXmlApplicationContext("applicationContext.xml");
	}

}
