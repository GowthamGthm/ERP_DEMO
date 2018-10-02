package com.obaba.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obaba.erp.daoImpl.UserDAOImpl;
import com.obaba.erp.entities.UserAuth;
import com.obaba.erp.service.IAuthService;


@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	UserDAOImpl userDaoImpl;

	@Override
	public int insertUser(UserAuth userAuth) {

		int id;
		try {

			id = userDaoImpl.insertUser(userAuth);

		} catch (Exception e) {
			throw e;
		}

		return id;

	}

	@Override
	public UserAuth checkAuth(UserAuth userAuth) {
		
		
		UserAuth user = null ;
		try {

			user =	 userDaoImpl.checkAuth(userAuth);

		} catch (Exception e) {
			throw e;
		}
		
		return user ;

	}

}
