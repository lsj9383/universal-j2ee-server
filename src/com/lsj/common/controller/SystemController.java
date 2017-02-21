package com.lsj.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsj.common.interceptor.Authority;
import com.lsj.common.interceptor.AuthorityType;

@Controller
public class SystemController {
	@Authority(type = AuthorityType.NoValidata)
	@RequestMapping("sys/turn.do")
	public String turnToPage(Model model, String msg){
		if(msg != null){
			model.addAttribute(msg);	
		}
		return "common/turn";
	}
}
