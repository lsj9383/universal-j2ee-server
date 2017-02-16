package com.lsj.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsj.user.model.User;
import com.lsj.user.service.LoginService;

@Controller
public class LoginController {
	
	@Resource(name="loginService")
	private LoginService service;
	
	@RequestMapping("login.do")
	public void login(HttpServletRequest request, String username, String password){
		User user = service.validateUser(username, password);
		if(user == null){
			System.out.println("login error");
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			System.out.println("login ok");
		}
	}
}
