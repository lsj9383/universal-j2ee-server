package com.lsj.common.interceptor;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.lsj.common.StaticResource;
import com.lsj.system.FileLoader;

@Service
public class StartAppListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private JdbcTemplate jt;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		WebApplicationContext webApplicationContext = (WebApplicationContext)event.getApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		loadResource();
		StaticResource.context = servletContext.getContextPath();
		StaticResource.appRealPath = servletContext.getRealPath("/");
		StaticResource.luaScript = new FileLoader(new File(StaticResource.appRealPath+"/WEB-INF/lua"));
	}
	
	private void loadResource(){
		List<Map<String, Object>> resList = jt.queryForList("select * from resources");
		for(Map<String, Object> res : resList){
			StaticResource.urls.put((String)res.get("url"), res);
		}
	}
}
