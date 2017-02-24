package com.lsj.login.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lsj.common.model.Resource;
import com.lsj.system.JavabeanUtil;

@Repository
public class IndexDao {
	
	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	NamedParameterJdbcTemplate njt;
	
	public List<Resource> listResource(){
		String sql = "select * from resources";
		List<Map<String, Object>> listMap = jt.queryForList(sql);
		List<Resource> list = new ArrayList<Resource>();
		
		for(Map<String, Object> itemMap : listMap){
			Resource resource = new Resource();
			resource.setSid((Integer)itemMap.get("sid"));
			resource.setParentId((Integer)itemMap.get("parent_id"));
			resource.setName((String)itemMap.get("name"));
			resource.setUrl((String)itemMap.get("url"));
			resource.setCksPower((Integer)itemMap.get("cks_power"));
			resource.setDispalyStatus((String)itemMap.get("display_status"));
			resource.setEnableStatus((String)itemMap.get("enable_status"));
			resource.setRemarks((String)itemMap.get("remarks"));
			list.add(resource);
		}
		
		return list;
	}
	
}
