package com.obaba.erp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obaba.erp.daoImpl.UserDAOImpl;
import com.obaba.erp.entities.TUser;
import com.obaba.erp.service.IAuthService;


@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	UserDAOImpl userDaoImpl;

	@Override
	public int insertUser(TUser userAuth) {

		int id;
		try {

			id = userDaoImpl.insertUser(userAuth);

		} catch (Exception e) {
			throw e;
		}

		return id;

	}

	@Override
	public TUser checkAuth(TUser userAuth) {
		
		
		TUser user = null ;
		try {

			user =	 userDaoImpl.checkAuth(userAuth);

		} catch (Exception e) {
			throw e;
		}
		
		return user ;

	}

}
