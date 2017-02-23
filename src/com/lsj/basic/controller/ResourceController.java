package com.lsj.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.basic.service.ResourceService;
import com.lsj.common.interceptor.Authority;
import com.lsj.common.interceptor.AuthorityType;
import com.lsj.system.JsonForReturn;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	ResourceService resourceService;
	
	/**
	 * 设置基本资源，指浏览所有资源以及添加资源
	 */
	@ResponseBody
	@Authority(type=AuthorityType.NoValidata)
	@RequestMapping("restBasicRes.do")
	public JsonForReturn restFoundResource(){
		JsonForReturn json = new JsonForReturn();
		json.data = resourceService.restFoundResource();
		return json;
	}
	
	@GetMapping("listview.do")
	public String listResource(){
		return "resource/listview";
	}
}
