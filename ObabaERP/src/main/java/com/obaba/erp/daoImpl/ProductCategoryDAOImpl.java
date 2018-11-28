package com.obaba.erp.daoImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.obaba.erp.entities.TProductSubCategory;
import com.obaba.erp.entities.TProducts;

@Repository
@Transactional
/* public class ProductCategoryDAOImpl implements IProductCategoryDAO { */

// public interface ProductCategoryDAOImpl extends
// JpaRepository<TProductCategory, Integer> {

public class ProductCategoryDAOImpl implements IProductCategoryDAO {
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<TProductCategory> getProductCategory() {

		Session session = null;
		List<TProductCategory> productCategoryList = null;
		List<TProductCategory> tProductCategory = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(TProductCategory.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			// .setProjection(Projections.distinct(Projections.property("categoryId")));
			productCategoryList = criteria.list();

			/*tProductCategory = productCategoryList.stream().map(product -> {

				TProductCategory productCat = new TProductCategory(product.getCategoryId(), product.getCategoryName(),
						product.getCategoryDesc(), product.getCreateDate(), product.getCreatedBy(),
						product.getUpdateDate(), product.getUpdatedBy(), null);

				List<TProductSubCategory> tProductCategories = new ArrayList<TProductSubCategory>();
				

				product.getTProductCategories().forEach(element -> {
					tProductCategories.add(element);
				});

				productCat.setTProductCategories(tProductCategories);
				return productCat;

			}).collect(Collectors.toList());*/

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return tProductCategory;
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
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("isActive", 'Y'))
					.add(Restrictions.eq("isHome", 'Y'));
			products = criteria.list();

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TProducts> getProductsByID(int productID) {
		Session session = null;
		List<TProducts> products = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(TProducts.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("isActive", 'Y'))
					.add(Restrictions.eq("productID", productID));
			products = criteria.list();

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TProducts getProductByID(int productID) {
		Session session = null;
		TProducts product = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(TProducts.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("isActive", 'Y'))
					.add(Restrictions.eq("productID", productID));
			product = (TProducts) criteria.uniqueResult();

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return product;
	}

	@Override
	public int addProductCategory(TProductCategory productCategory) {
		Session session = null;
		int createdId;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			createdId = (int) session.save(productCategory);

			session.flush();
			session.getTransaction().commit();

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return createdId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TProductCategory> getAllProductCategories() {
		Session session = null;

		List<TProductCategory> categories = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria criteria = session.createCriteria(TProductCategory.class);
			categories = criteria.list();

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return categories;
	}

	@Override
	public int addProductSubCategory(TProductSubCategory subcategory) {
		Session session = null;
		int createdId;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			createdId = (int) session.save(subcategory);

			session.flush();
			session.getTransaction().commit();

		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return createdId;

	}

}
