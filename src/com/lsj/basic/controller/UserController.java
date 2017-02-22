package com.lsj.basic.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.basic.service.UserService;
import com.lsj.common.interceptor.Authority;
import com.lsj.common.interceptor.AuthorityType;
import com.lsj.system.JsonForReturn;

@Controller
public class UserController {
	
	@Resource(name="userService")
	UserService userService;
	
	@ResponseBody
	@Authority(type=AuthorityType.NoValidata)
	@RequestMapping("restAdmin.do")
	public JsonForReturn restAdmin(String password){
		JsonForReturn json = new JsonForReturn();
		if(password == null || password.length()==0){
			password = "admin";
		}
		json.data = userService.restAdmin(password);
		return json;
	}

}
