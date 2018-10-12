package com.obaba.erp.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.obaba.erp.dao.IUserDAO;
import com.obaba.erp.entities.TUser;

@Repository
@Transactional
public class UserDAOImpl implements IUserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int insertUser(TUser userAuth) {

		Session session = null;
		Integer id = 0;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(TUser.class);
			criteria.add(Restrictions.eq("userName", userAuth.getUserName()));
			TUser auth = (TUser) criteria.uniqueResult();

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
	public TUser checkAuth(TUser userAuth) {

		Session session = null;

		TUser user = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(TUser.class);
			criteria.add(Restrictions.eq("userName", userAuth.getUserName()));
			criteria.add(Restrictions.eq("password", userAuth.getPassword()));

			user = (TUser) criteria.uniqueResult();

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return user;

	}

}
