package com.obaba.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.obaba.erp.dao.IProductCategoryDAO;
import com.obaba.erp.entities.ProductCategoryEntity;

@Repository
@Transactional
public class ProductCategoryDAOImpl implements IProductCategoryDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	

	@Override
	public List<ProductCategoryEntity> getProductCategory() {
		
		Session session = null;
		List<ProductCategoryEntity> productCategoryList = null;
		
		try {
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(ProductCategoryEntity.class);
			productCategoryList =  criteria.list();
			
			
		}catch (Exception e) {
			throw e ;
		}finally {
			session.close();
		}
		return productCategoryList;
	}

}
