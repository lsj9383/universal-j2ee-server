package com.lsj.login.model;

import java.util.LinkedList;
import java.util.List;

import com.lsj.common.model.Resource;

public class ResourceNode {
	
	public final List<ResourceNode> children = new LinkedList<ResourceNode>();
	public final Resource resource;
	
	public ResourceNode(Resource res){
		resource = res;
	}

}
