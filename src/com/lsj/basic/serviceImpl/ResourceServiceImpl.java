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
		Resource[] ress = new Resource[]{new Resource(), new Resource(), new Resource(), new Resource()};
		ress[0].setSid(1);
		ress[0].setParentId(0);
		ress[0].setName("基本管理");
		ress[0].setCksPower(0);
		ress[0].setDispalyStatus("1");
		ress[0].setEnableStatus("1");
		ress[0].setRemarks("基本资源管理");
		
		ress[1].setSid(2);
		ress[1].setParentId(1);
		ress[1].setName("资源管理");
		ress[1].setUrl("/resource/listview.do");
		ress[1].setCksPower(1);
		ress[1].setDispalyStatus("1");
		ress[1].setEnableStatus("1");
		ress[1].setRemarks("列出所有资源");
		
		ress[2].setSid(3);
		ress[2].setParentId(1);
		ress[2].setName("addResource");
		ress[2].setUrl("/resource/add.do");
		ress[2].setCksPower(2);
		ress[2].setDispalyStatus("0");
		ress[2].setEnableStatus("1");
		ress[2].setRemarks("添加一个资源");
		
		ress[3].setSid(4);
		ress[3].setParentId(1);
		ress[3].setName("indexResource");
		ress[3].setUrl("/indexview.do");
		ress[3].setCksPower(3);
		ress[3].setDispalyStatus("0");
		ress[3].setEnableStatus("1");
		ress[3].setRemarks("显示主页面");
		
		dao.delFoundResorces();
		return dao.addResources(ress);
	}

}
