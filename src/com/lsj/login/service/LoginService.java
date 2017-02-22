package com.lsj.login.service;

import com.lsj.common.model.User;

public interface LoginService {
	User validateUser(String username, String password);
}
