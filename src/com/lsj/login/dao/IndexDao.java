package com.lsj.login.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lsj.common.model.Resource;

@Repository
public class IndexDao {
	
	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	NamedParameterJdbcTemplate njt;
	
	private RowMapper<Resource> resourceMapper = new BeanPropertyRowMapper<Resource>(Resource.class);
	
	public List<Resource> listResource(){
		String sql = "select * from resources";
		List<Resource> list = jt.query(sql, resourceMapper);
		return list;
	}
	
}
