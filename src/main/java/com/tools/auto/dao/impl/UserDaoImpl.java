package com.tools.auto.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tools.auto.dao.UserDao;
import com.tools.auto.model.User;

/**
* Author: fulishang
* Create Time  : 2017年5月13日,下午4:01:06
* Modify Time :
* Desc  : 用户操作数据库
* Blog : https://lishang08.github.io/
*/

public class UserDaoImpl implements UserDao{
	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	private JdbcTemplate jdbcTemplate;

	/**
	 * 检索数据库，验证登录
	 */
	public User checkLogin(ApplicationContext context, String name, String password) {
		//query sql
		String sql = "select username, password from user where username=? and password=?";
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		logger.info("Running sql ==>" + sql);
		List<User> users = jdbcTemplate.query(sql, new Object[] { name, password }, new RowMapper<User>(){
			public User mapRow(ResultSet rs, int num) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		
		User user = null;
		if (users != null && users.size()>0) {
			user = users.get(0);
		}
		return user;
	}

}
