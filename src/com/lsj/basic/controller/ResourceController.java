package com.lsj.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.basic.service.ResourceService;
import com.lsj.common.interceptor.Authority;
import com.lsj.common.interceptor.AuthorityType;
import com.lsj.common.model.Resource;
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
	@RequestMapping("restBasic.do")
	public JsonForReturn restFoundResource(){
		JsonForReturn json = new JsonForReturn();
		json.data = resourceService.restFoundResource();
		return json;
	}
	
	@GetMapping("addview.do")
	public String addView(){
		return "resource/addview";
	}
	
	@GetMapping("listview.do")
	public String listResource(Model model){
		List<Resource> list = resourceService.listAll();
		Resource[] resources = new Resource[list.size()];
		list.toArray(resources);
		model.addAttribute("resourceList", list);
		return "resource/listview";
	}
	
	
	@ResponseBody
	@PostMapping("add.do")
	public JsonForReturn add(Resource resource){
		JsonForReturn json = new JsonForReturn();
		json.data = resourceService.add(resource);
		if(json.data == null){
			json.flag = false;
			json.msg = "error";
		}
		return json;
	} 
}
