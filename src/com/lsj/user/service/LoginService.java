package com.lsj.user.service;

import com.lsj.user.model.User;

public interface LoginService {
	User validateUser(String username, String password);
}
