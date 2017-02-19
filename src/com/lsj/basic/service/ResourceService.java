package com.lsj.basic.service;

import java.util.List;

import com.lsj.common.model.Resource;

public interface ResourceService {
	
	Resource[] restFoundResource();
	List<Resource> listAll();
}
