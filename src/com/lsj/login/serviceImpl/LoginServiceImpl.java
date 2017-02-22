package com.lsj.login.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsj.common.model.User;
import com.lsj.login.dao.LoginDao;
import com.lsj.login.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public User validateUser(String username, String password) {
		return loginDao.SearchUser(username, password);
	}
}
