package com.obaba.erp.dao;

import com.obaba.erp.entities.UserAuth;

public interface IUserDAO {

	public int  insertUser(UserAuth userAuth);
	
	public UserAuth checkAuth(UserAuth userAuth);
	
}
