package com.lsj.user.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsj.user.dao.LoginDao;
import com.lsj.user.model.User;
import com.lsj.user.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public User validateUser(String username, String password) {
		return loginDao.SearchUser(username, password);
	}
}
