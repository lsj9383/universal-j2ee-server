package com.lsj.login.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsj.common.model.Resource;
import com.lsj.login.dao.IndexDao;
import com.lsj.login.model.ResourceNode;
import com.lsj.login.service.IndexService;

@Service("indexService")
public class IndexServiceImpl implements IndexService {

	@Autowired
	IndexDao dao;
	
	@Override
	public ResourceNode getResourceTree() throws Exception {
		Map<Integer, ResourceNode> resourceMap = new HashMap<Integer, ResourceNode>();
		List<Resource> list = dao.listResource();
		
		//添加所有的节点
		resourceMap.put(0, new ResourceNode(null));		//此处为根节点，数据库需保证
		for(Resource item : list){
			if(item.getSid() == 0){
				throw new Exception("资源的id占用了根节点的id");
			}
			resourceMap.put(item.getSid(), new ResourceNode(item));
		}
		
		//将所有节点进行调控
		for(Resource item : list){
			ResourceNode pnode = resourceMap.get(item.getParentId());
			ResourceNode snode = resourceMap.get(item.getSid());
			pnode.children.add(snode);
		}
		
		//返回根节点
		return resourceMap.get(0);
	}

}
