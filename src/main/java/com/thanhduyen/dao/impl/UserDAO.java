package com.thanhduyen.dao.impl;

import java.util.List;

import com.thanhduyen.dao.IUserDAO;
import com.thanhduyen.mapper.NewMapper;
import com.thanhduyen.mapper.UserMapper;
import com.thanhduyen.model.NewModel;
import com.thanhduyen.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findUsernameAndPassword(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);
		return users.isEmpty() ? null : users.get(0);
	}

	
}