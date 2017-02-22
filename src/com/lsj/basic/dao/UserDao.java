package com.lsj.basic.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lsj.common.model.User;

@Repository
public class UserDao {
	
	@Autowired
	JdbcTemplate jt;
	@Autowired
	NamedParameterJdbcTemplate njt;
	
	public User restAdmin(User admin){
		//清除原来的admin
		String delSql = "delete from users where username=?";
		jt.update(delSql, "admin");
		
		//创建新的admin
		String sql="insert into users (username, password, power) values (:username, :password, :power)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource params = new BeanPropertySqlParameterSource(admin);
		njt.update(sql, params, keyHolder);
		if(keyHolder != null && keyHolder.getKey() != null){
			admin.setSid(keyHolder.getKey().intValue());
		}
		return admin;
	}

}
