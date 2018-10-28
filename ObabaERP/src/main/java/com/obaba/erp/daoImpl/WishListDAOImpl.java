package com.obaba.erp.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.obaba.erp.dao.IWishListDAO;
import com.obaba.erp.entities.TUser;
import com.obaba.erp.entities.TWishlist;

@Repository
@Transactional
public class WishListDAOImpl implements IWishListDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int insertWish(TWishlist tWishList) {

		Session session = null;
		Integer id = 0;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			id = (Integer) session.save(tWishList);
			session.getTransaction().commit();

		} catch (Exception e) {
			throw e;
		}

		finally {
			session.close();
		}
		return id;

	}

}
