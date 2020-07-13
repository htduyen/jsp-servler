package com.thanhduyen.dao;

import com.thanhduyen.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
	
	UserModel findUsernameAndPassword(String username, String password, Integer status);
}
