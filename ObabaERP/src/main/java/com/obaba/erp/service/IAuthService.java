package com.obaba.erp.service;

import com.obaba.erp.entities.TUser;

public interface IAuthService {

	public int insertUser(TUser userAuth);
	public TUser checkAuth(TUser userAuth);

}
