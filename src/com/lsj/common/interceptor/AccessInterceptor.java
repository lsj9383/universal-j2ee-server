package com.lsj.common.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
		if(urlResource == null){
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
		String url = "sys/turn.do";
		switch(type){
		case ERROR:
			System.out.println("未知错误");
			response.sendRedirect(url);
			break;
		case NOLOGGED:
			System.out.println("未登录");
			response.sendRedirect(url);
			break;
		case NOPOWER:
			System.out.println("无权限");
			break;
		default :
			break;
		}
	}
	
	public enum Type{
		ERROR, NOLOGGED, NOPOWER, SUCCESS
	}
}
