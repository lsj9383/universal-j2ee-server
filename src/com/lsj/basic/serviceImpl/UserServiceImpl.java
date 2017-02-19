package com.lsj.basic.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsj.basic.dao.UserDao;
import com.lsj.basic.service.UserService;
import com.lsj.common.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;
	
	@Override
	public User restAdmin(String password) {
		User admin = new User();
		String power = "";
		admin.setUsername("admin");
		admin.setPassword(password);
		for(int i=0; i<100; i++){
			power += "11111";
		}
		admin.setPower(power);
		return dao.restAdmin(admin);
	}

	@Override
	public List<User> list() {
		return dao.list();
	}

}
