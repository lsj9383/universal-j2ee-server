package com.lsj.basic.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsj.basic.dao.ResourceDao;
import com.lsj.basic.service.ResourceService;
import com.lsj.common.model.Resource;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	ResourceDao dao;
	
	@Override
	public Resource[] restFoundResource() {
		Resource[] ress = new Resource[]{new Resource(), new Resource()};
		ress[0].setName("listResource");
		ress[0].setUrl("/resource/list.do");
		ress[0].setCksPower(0);
		ress[0].setDispalyStatus("1");
		ress[0].setEnableStatus("1");
		ress[0].setRemarks("列出所有资源");
		
		ress[1].setName("listResource");
		ress[1].setUrl("/resource/add.do");
		ress[1].setCksPower(1);
		ress[1].setDispalyStatus("0");
		ress[1].setEnableStatus("1");
		ress[1].setRemarks("添加一个资源");
		
		dao.delFoundResorces();
		return dao.addResources(ress);
	}

}
