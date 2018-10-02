package com.obaba.erp.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.obaba.erp.dao.IUserDAO;
import com.obaba.erp.entities.UserAuth;

@Repository
@Transactional
public class UserDAOImpl implements IUserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int insertUser(UserAuth userAuth) {

		Session session = null;
		Integer id = 0;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(UserAuth.class);
			criteria.add(Restrictions.eq("userName", userAuth.getUserName()));
			UserAuth auth = (UserAuth) criteria.uniqueResult();

			if (auth == null) {
				id = (Integer) session.save(userAuth);
				session.getTransaction().commit();
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw e;
		}

		finally {
			session.close();
		}
		return id;

	}

	@Override
	public UserAuth checkAuth(UserAuth userAuth) {

		Session session = null;

		UserAuth user = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(UserAuth.class);
			criteria.add(Restrictions.eq("userName", userAuth.getUserName()));
			criteria.add(Restrictions.eq("password", userAuth.getPassword()));

			user = (UserAuth) criteria.uniqueResult();

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return user;

	}

}
