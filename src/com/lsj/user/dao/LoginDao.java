package com.lsj.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.lsj.user.model.*;

@Repository
public class LoginDao {
	@Autowired
	private JdbcTemplate jt;
	
	private RowMapper<User> userBeanMapper = new BeanPropertyRowMapper<User>(User.class);
	
	public User SearchUser(String username, String password){
		String sql = "select * from users where username=? and password=?";
		User user = null;
		try{
			user = jt.queryForObject(sql, userBeanMapper, username, password);
		}catch(Throwable e){
			user = null;
		}
		
		return user;
	}
}
