package com.obaba.erp.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.obaba.erp.dao.IProductCategoryDAO;
import com.obaba.erp.entities.TProductCategory;

@Repository
@Transactional
/*public class ProductCategoryDAOImpl implements IProductCategoryDAO {*/
	
public interface ProductCategoryDAOImpl extends JpaRepository<TProductCategory, Integer> {

	/*@Autowired
	SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<TProductCategory> getProductCategory() {
		
		Session session = null;
		List<TProductCategory> productCategoryList = null;
		
		try {
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			//Hibernate.initialize(TProductCategory.getTProductSubCategories);
			
			Criteria criteria = session.createCriteria(TProductCategory.class);
			productCategoryList =  criteria.list();
			
		}catch (Exception e) {
			throw e ;
		}finally {
			//session.close();
		}
		return productCategoryList;
	}*/

}
