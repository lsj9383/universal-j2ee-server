package com.lsj.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.lsj.common.StaticResource;
import com.lsj.common.interceptor.Authority;
import com.lsj.common.interceptor.AuthorityType;
import com.lsj.system.JsonForReturn;

@Controller
public class SystemController {
	@Authority(type = AuthorityType.NoValidata)
	@RequestMapping("/sys/turn.do")
	public String turnToPage(Model model, Integer sec, String target, String msg){
		if(sec==null){
			sec = 3;
		}
		model.addAttribute("msg", msg);
		model.addAttribute("target", target);
		model.addAttribute("sec", sec);
		return "common/turn";
	}
	
	@Authority(type = AuthorityType.NoValidata)
	@ResponseBody
	@RequestMapping("/sys/showCount.do")
	public JsonForReturn showCount(){
		JsonForReturn json = new JsonForReturn();
		Jedis jedis = StaticResource.jedisPool.getResource();
		Map<String, String> urlCountMap = new HashMap<String, String>();
		for(String key : jedis.keys("url:*:count")){
			String count = jedis.get(key);
			if(count != null){
				urlCountMap.put(key, count);	
			}
		}
		jedis.close();
		json.data = urlCountMap;
		return json;
	}
}
