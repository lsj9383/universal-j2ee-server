package com.lsj.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
	
	private RowMapper<Resource>  resourceMapper = new BeanPropertyRowMapper<Resource>(Resource.class);
	
	public int delFoundResorces(){
		String delSql = "delete from resources where sid=? or sid=? or sid=? or sid=? or sid=?";
		return jt.update(delSql, "1", "2", "3", "4", "5");
	}
	
	public Resource[] addResources(Resource[] ress){
		for(Resource res : ress){
			String sql = "insert into resources (sid, parent_id, name, url, cks_power, display_status, enable_status, remarks) values (:sid, :parentId, :name, :url, :cksPower, :dispalyStatus, :enableStatus, :remarks)";
			SqlParameterSource params = new BeanPropertySqlParameterSource(res);
			njt.update(sql, params);
		}
		return ress;
	}
	
	public List<Resource> listAll(){
		List<Resource> list = jt.query("select * from resources", resourceMapper);
		return list;
	}
	
	public Resource add(Resource resource){
		String sql = "select sid from resources order by sid desc";
		List<Integer> sids = jt.queryForList(sql, Integer.class);
		if(sids.size() == 0){
			resource.setSid(10);
		}else{
			resource.setSid(sids.get(0)+1);
			resource.setCksPower(sids.get(0));
		}
		Resource[] ress = addResources(new Resource[]{resource});
		if(ress == null || ress.length == 0){
			return null;
		}
		return ress[0];
	}
}
