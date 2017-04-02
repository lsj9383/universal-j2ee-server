package com.lsj.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequest extends HttpServletRequestWrapper {

	public XssHttpServletRequest(HttpServletRequest request) {
		super(request);
	}

}
