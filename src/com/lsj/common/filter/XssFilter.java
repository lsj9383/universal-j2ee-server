package com.lsj.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XssFilter implements Filter{

	FilterConfig filterConfig = null;
	
	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig config) throws ServletException {
		filterConfig = config;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new XssHttpServletRequest((HttpServletRequest) request), response);
	}
}
