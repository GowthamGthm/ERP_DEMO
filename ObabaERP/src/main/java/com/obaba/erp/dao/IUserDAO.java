package com.obaba.erp.dao;

import com.obaba.erp.entities.TUser;

public interface IUserDAO {

	public int  insertUser(TUser userAuth);
	
	public TUser checkAuth(TUser userAuth);
	
}
