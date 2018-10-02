package com.obaba.erp.service;

import com.obaba.erp.entities.UserAuth;

public interface IAuthService {

	public int insertUser(UserAuth userAuth);
	public UserAuth checkAuth(UserAuth userAuth);

}
