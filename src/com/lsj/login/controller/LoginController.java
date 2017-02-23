package com.lsj.login.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsj.common.interceptor.Authority;
import com.lsj.common.interceptor.AuthorityType;
import com.lsj.common.model.User;
import com.lsj.login.service.LoginService;

@Controller
public class LoginController {
	
	@Resource(name="loginService")
	private LoginService service;
	
	@Authority(type=AuthorityType.NoValidata)
	@RequestMapping("login.do")
	//remeber user暂且未实现
	public String login(HttpServletRequest request, HttpServletResponse response, String username, String password, boolean remember) throws IOException{
		User user = service.validateUser(username, password);
		if(user == null){
			response.sendRedirect("loginview.do");
			return null;
		}else{
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			System.out.println(username+password+remember);
			response.sendRedirect("indexview.do");
			return null;
		}
	}
	
	@Authority(type=AuthorityType.NoValidata)
	@RequestMapping("loginview.do")
	public String login(HttpServletRequest request, Model model){
		return "loginview";
	}
	
	@RequestMapping("indexview.do")
	public String index(){
		return "indexview";
	}
}
