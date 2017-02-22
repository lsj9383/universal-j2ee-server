package com.lsj.basic.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.basic.service.ResourceService;
import com.lsj.common.interceptor.Authority;
import com.lsj.common.interceptor.AuthorityType;
import com.lsj.system.JsonForReturn;

@Controller
public class ResourceController {

	@Resource(name="resourceService")
	ResourceService resourceService;
	
	/**
	 * 设置基本资源，指浏览所有资源以及添加资源
	 */
	@ResponseBody
	@Authority(type=AuthorityType.NoValidata)
	@RequestMapping("restFoundResource.do")
	public JsonForReturn restFoundResource(){
		JsonForReturn json = new JsonForReturn();
		json.data = resourceService.restFoundResource();
		return json;
	}
	
}
