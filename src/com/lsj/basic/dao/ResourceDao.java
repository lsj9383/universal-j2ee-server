package com.lsj.basic.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lsj.common.model.Resource;

@Repository
public class ResourceDao {
	
	@Autowired
	JdbcTemplate jt;
	@Autowired
	NamedParameterJdbcTemplate njt;
	
	public int delFoundResorces(){
		String delSql = "delete from resources where sid=? or sid=? or sid=? or sid=?";
		return jt.update(delSql, "1", "2", "3", "4");
	}
	
	public Resource[] addResources(Resource[] ress){
		for(Resource res : ress){
			String sql = "insert into resources (sid, parent_id, name, url, cks_power, display_status, enable_status, remarks) values (:sid, :parentId, :name, :url, :cksPower, :dispalyStatus, :enableStatus, :remarks)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource params = new BeanPropertySqlParameterSource(res);
			njt.update(sql, params, keyHolder);
			if(keyHolder != null && keyHolder.getKey() != null){
				res.setSid(keyHolder.getKey().intValue());
			}
		}
		return ress;
	}
}
