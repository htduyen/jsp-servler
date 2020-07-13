package com.thanhduyen.service.impl;

import javax.inject.Inject;

import com.thanhduyen.dao.IUserDAO;
import com.thanhduyen.model.UserModel;
import com.thanhduyen.service.IUserService;

public class UserService implements IUserService{

	@Inject
	private IUserDAO userDAO;
	
	@Override
	public UserModel findUsernameAndPassword(String username, String password, Integer status) {
		
		return userDAO.findUsernameAndPassword(username, password, status);
	}

}
