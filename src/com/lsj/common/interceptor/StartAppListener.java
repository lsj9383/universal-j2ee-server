package com.lsj.common.interceptor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lsj.common.StaticResource;

@Service
public class StartAppListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private JdbcTemplate jt;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			loadResource();
		}else{
			System.out.println(event.getApplicationContext().getParent());
		}
	}
	
	private void loadResource(){
		List<Map<String, Object>> resList = jt.queryForList("select * from resources");
		for(Map<String, Object> res : resList){
			StaticResource.urls.put((String)res.get("url"), res);
		}
	}


}
