package com.obaba.erp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obaba.erp.dao.IProductCategoryDAO;
import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProducts;
import com.obaba.erp.service.IProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

	@Autowired
	IProductCategoryDAO productCategoryDaoImpl;

	@Override
	public List<TProductCategory> getListOfCategories() {
		List<TProductCategory> productsCategoryList = null;
		try {
			productsCategoryList = productCategoryDaoImpl.getProductCategory();

		} catch (Exception e) {
			throw e;
		}

		return productsCategoryList;
	}

	public List<TProducts> getHomeProducts() {
		List<TProducts> products = null;
		try {
			products = productCategoryDaoImpl.getHomeProducts();
		} catch (Exception e) {
			throw e;
		}
		return products;
	}

	@Override
	public List<TProducts> getProductsByID(int productID) {
		List<TProducts> products = null;
		try {

			products = productCategoryDaoImpl.getProductByID(productID);
			
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

}
