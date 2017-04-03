package com.lsj.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsj.common.interceptor.Authority;
import com.lsj.common.interceptor.AuthorityType;
import com.lsj.common.model.User;
import com.lsj.login.service.IndexService;
import com.lsj.login.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private IndexService indexService;
	
	
	@Authority(type=AuthorityType.NoValidata)
	@RequestMapping("login.do")
	//remeber user暂且未实现
	public String login(HttpServletRequest request, HttpServletResponse response, String username, String password, boolean remember) throws IOException{
		User user = loginService.validateUser(username, password);
		if(user == null){
			response.sendRedirect("loginview.do");
			return null;
		}else{
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
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
	public String index(HttpServletRequest request, Model model) throws Exception{
		model.addAttribute("resource", indexService.getResourceTree());
		return "indexview";
	}
}
