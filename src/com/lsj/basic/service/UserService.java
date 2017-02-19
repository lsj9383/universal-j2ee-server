package com.lsj.basic.service;

import java.util.List;

import com.lsj.common.model.User;

public interface UserService {
	User restAdmin(String password);
	List<User> list();
}
