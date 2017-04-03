package com.lsj.common.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import redis.clients.jedis.Jedis;

import com.lsj.common.StaticResource;
import com.lsj.common.model.User;


public class AccessInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod){
			request.getServletContext().setAttribute("serverRoot", request.getServletContext().getContextPath());
			HandlerMethod hm = (HandlerMethod) handler;
			Method execMethod = hm.getMethod();			//待运行的方法
			logAccess(request.getRequestURI());
			Authority authority = execMethod.getAnnotation(Authority.class);
			if(authority == null || authority.type() == AuthorityType.Validate){	//注解为空或为Validate就需要认证
				Type type = superValidate(request, request.getServletPath());
				if(type == Type.SUCCESS){	//验证成功，放行
					return true;
				}else{						//验证失败，转到失败页面
					turenToErrorPage(response, type);
					return false;
				}
			}else if(authority.type()==AuthorityType.NoValidata){
				//不需要认证 直接放行
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	private Type superValidate(HttpServletRequest request, String url){
		HttpSession session = request.getSession();
		Map<String, Object> urlResource = StaticResource.urls.get(url);
		User user = (User)session.getAttribute("user");
		
		if(limitIP(request.getRemoteAddr())){
			return Type.LIMIT;
		}else if(urlResource == null){
			return Type.ERROR;
		}else if(user == null){				//session中没有用户，则用户还没有登录，需要登录.
			return Type.NOLOGGED;
		}else{
			if(user.getPower().charAt((Integer)urlResource.get("cks_power")) == 0){
				return Type.NOPOWER;	//
			}else{
				return Type.SUCCESS;	//
			}
		}
	}
	
	private void turenToErrorPage(HttpServletResponse response, Type type) throws IOException{
		String url = "/jser/sys/turn.do?sec=3";
		String target="&target=";
		String msg="&msg=";
		String redUrl=null;
		switch(type){
		case ERROR:
			System.out.println("未知错误");
			response.sendRedirect(url);
			break;
		case NOLOGGED:
			System.out.println("未登录");
			redUrl = url+target+"/jser/loginview.do"+msg+"not logged";
			response.sendRedirect(redUrl);
			break;
		case NOPOWER:
			System.out.println("无权限");
			redUrl = url+target+"/jser/indexview.do"+msg+"no power";
			response.sendRedirect(redUrl);
			break;
		case LIMIT:
			System.out.println("限制访问");
			redUrl = url+target+"/jser/indexview.do"+msg+"limit IP";
			response.sendRedirect(redUrl);
			break;
		default :
			break;
		}
	}
	
	private void logAccess(String uri){
		Jedis jedis = StaticResource.jedisPool.getResource();
		jedis.incr("url:"+uri+":count");
		jedis.close();   //返回给连接池
	}
	
	private boolean limitIP(String ip){
		Jedis jedis = StaticResource.jedisPool.getResource();
		Object res = jedis.eval(StaticResource.luaScript.get("coarse-limitIP.lua"), Arrays.asList(ip), Arrays.asList("30", "100"));	//100秒运行访问30次
		jedis.close();
		return ((Long)res) == 1;
	}
	
	public enum Type{
		ERROR, NOLOGGED, NOPOWER, LIMIT, SUCCESS
	}
}
