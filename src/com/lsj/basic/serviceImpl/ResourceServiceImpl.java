package com.lsj.basic.serviceImpl;

import java.util.List;

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
		Resource[] ress = new Resource[]{new Resource(), new Resource(), new Resource(), new Resource(), new Resource()};
		ress[0].setSid(1);
		ress[0].setParentId(0);
		ress[0].setName("Main Control");
		ress[0].setCksPower(0);
		ress[0].setDispalyStatus("1");
		ress[0].setEnableStatus("1");
		ress[0].setRemarks("basic service");
		
		ress[1].setSid(2);
		ress[1].setParentId(1);
		ress[1].setName("index view");
		ress[1].setUrl("/indexview.do");
		ress[1].setCksPower(3);
		ress[1].setDispalyStatus("0");
		ress[1].setEnableStatus("1");
		ress[1].setRemarks("显示主页面");
		
		ress[2].setSid(3);
		ress[2].setParentId(1);
		ress[2].setName("resources display");
		ress[2].setUrl("/resource/listview.do");
		ress[2].setCksPower(1);
		ress[2].setDispalyStatus("1");
		ress[2].setEnableStatus("1");
		ress[2].setRemarks("list all resources and display");
		
		ress[3].setSid(4);
		ress[3].setParentId(1);
		ress[3].setName("addResource");
		ress[3].setUrl("/resource/add.do");
		ress[3].setCksPower(2);
		ress[3].setDispalyStatus("0");
		ress[3].setEnableStatus("1");
		ress[3].setRemarks("add resource");
		
		ress[4].setSid(5);
		ress[4].setParentId(1);
		ress[4].setName("resource add view");
		ress[4].setUrl("/resource/addview.do");
		ress[4].setCksPower(3);
		ress[4].setDispalyStatus("0");
		ress[4].setEnableStatus("1");
		ress[4].setRemarks("显示主页面");
		
		dao.delFoundResorces();
		return dao.addResources(ress);
	}

	@Override
	public List<Resource> listAll() {
		return dao.listAll();
	}

	@Override
	public Resource add(Resource resource) {
		return dao.add(resource);
	}

}
