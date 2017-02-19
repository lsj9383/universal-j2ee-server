package com.lsj.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public String login(HttpServletRequest request, HttpServletResponse response, String username, String password, boolean remember){
		User user = service.validateUser(username, password);
		if(user == null){
			return "redirect: loginview.do";
		}else{
			HttpSession session = request.getSession(true);
			if(remember){
				session.setAttribute("user", user);
			}
			System.out.println(username+password+remember);
			return "redirect: home";
		}
	}
	
	@RequestMapping("loginview.do")
	public String login(){
		return "login";
	}
}
