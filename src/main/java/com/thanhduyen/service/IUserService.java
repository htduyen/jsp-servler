package com.thanhduyen.service;

import com.thanhduyen.model.UserModel;

public interface IUserService {
	
	UserModel findUsernameAndPassword(String username, String password, Integer status);

}
