package com.lsj.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lsj.common.interceptor.Authority;
import com.lsj.common.interceptor.AuthorityType;

@Controller
public class SqlController {
	
	@Autowired
	private JdbcTemplate jt;
	
	@Authority(type=AuthorityType.NoValidata)
	@RequestMapping("sql.do")
	public String sql(Model model, @RequestParam(defaultValue="")String tableName){
		String sql = "SELECT COLUMN_NAME as cname, DATA_TYPE as dtype FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=? AND TABLE_NAME=?";
		List<Map<String, Object>> mappers = jt.queryForList(sql, "cms", tableName);
		String selectSql = getSelectSql(tableName, mappers);
		String insertSql = getInsertSql(tableName, mappers);
		String javabean = getJavabean(tableName, mappers);
		model.addAttribute("selectSql", selectSql);
		model.addAttribute("insertSql", insertSql);
		model.addAttribute("javabean", javabean);
		return "common/sqlview";
	}
	
	private String getSelectSql(String tableName, List<Map<String, Object>> mappers){
		StringBuilder sb = new StringBuilder();
		sb.append("select");
		for(Map<String, Object> mapper : mappers){
			String cname = mapper.get("cname").toString();
			sb.append(" "+cname+",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" from "+tableName);
		return sb.toString();
	}
	
	private String getInsertSql(String tableName, List<Map<String, Object>> mappers){
		StringBuilder sb = new StringBuilder();
		sb.append("insert into "+tableName+" (");
		for(Map<String, Object> mapper : mappers){
			String cname = mapper.get("cname").toString();
			sb.append(cname+",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") values (");
		for(Map<String, Object> mapper : mappers){
			String cname = mapper.get("cname").toString();
			sb.append(":"+sqlName2JavaName(cname)+",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		return sb.toString();
	}
	
	private String getJavabean(String tableName, List<Map<String, Object>> mappers){
		StringBuilder sb = new StringBuilder();
		sb.append("public class "+sqlName2JavaName(tableName)+"{\n");
		for(Map<String, Object> mapper : mappers){
			String cname = mapper.get("cname").toString();
			String dtype = mapper.get("dtype").toString();
			
			if(dtype.equals("varchar")){
				sb.append("private String ");
			}else if(dtype.equals("int")){
				sb.append("private Integer ");
			}else if(dtype.equals("datetime")){
				sb.append("private Date ");
			}else{
				continue;
			}
			sb.append(sqlName2JavaName(cname)+";\n");
		}
		sb.append("}");
		return sb.toString();
	}
	
	private String sqlName2JavaName(String sqlName){
		String[] parts = sqlName.split("_");
		StringBuilder sb = new StringBuilder();
		sb.append(parts[0]);
		for(int i=1; i<parts.length; i++){
			sb.append(parts[i].substring(0, 1).toUpperCase()+parts[i].substring(1));
		}
		return sb.toString();
	}
}
