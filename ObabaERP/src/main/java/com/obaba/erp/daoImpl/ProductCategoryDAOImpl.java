package com.obaba.erp.daoImpl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.obaba.erp.dao.IProductCategoryDAO;
import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProducts;

@Repository
@Transactional
/*public class ProductCategoryDAOImpl implements IProductCategoryDAO {*/
	
//public interface ProductCategoryDAOImpl extends JpaRepository<TProductCategory, Integer> {

public class ProductCategoryDAOImpl implements IProductCategoryDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TProductCategory> getProductCategory() {
		
		Session session = null;
		List<TProductCategory> productCategoryList = null;
		
		try {
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(TProductCategory.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
					// .setProjection(Projections.distinct(Projections.property("categoryId")));
			productCategoryList = criteria.list();
			
		}catch (Exception e) {
			throw e ;
		}finally {
			session.close();
		}
		return productCategoryList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TProducts> getHomeProducts() {
		Session session = null;
		List<TProducts> products = null;
		
		try {
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(TProducts.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("isActive", 'Y'))
					.add(Restrictions.eq("isHome", 'Y'));
			products = criteria.list();
			
		}catch (Exception e) {
			throw e ;
		}finally {
			session.close();
		}
		return products;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TProducts> getProductByID(int productID) {
		Session session = null;
		List<TProducts> products = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(TProducts.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("isActive", 'Y'))
					.add(Restrictions.eq("productID", productID));
			products = criteria.list();
			
		}catch (Exception e) {
			throw e ;
		}finally {
			session.close();
		}
		return products;
	}

}
