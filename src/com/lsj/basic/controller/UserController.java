package com.lsj.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.basic.service.UserService;
import com.lsj.common.interceptor.Authority;
import com.lsj.common.interceptor.AuthorityType;
import com.lsj.common.model.User;
import com.lsj.system.JsonForReturn;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
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

	@Authority(type=AuthorityType.NoValidata)
	@PostMapping("addUser.do")
	public String registerUser(User user){
		System.out.println(user);
		return null;
	}
	
	@GetMapping("listview.do")
	public String listview(Model model){
		List<User> list = userService.list();
		model.addAttribute("users", list);
		return "user/listview.do";
	}
}
