package com.tools.auto.event;

import java.util.List;

/**
* Author: fulishang
* Create Time  : 2017年5月14日,下午10:51:32
* Modify Time :
* Desc  : 
* Blog : https://lishang08.github.io/
*/

public interface DocumentGenerator<T> {

	/** generate xml document */
	public void buildXml(List<T> list) throws Exception;
	
	/** generate ddl document */
	public void buildDDL(List<T> list) throws Exception;
}
