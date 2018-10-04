package com.obaba.erp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obaba.erp.daoImpl.ProductCategoryDAOImpl;
import com.obaba.erp.entities.ProductCategoryEntity;
import com.obaba.erp.service.IProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
	
	@Autowired
	ProductCategoryDAOImpl productCategoryDAO;

	@Override
	public List<ProductCategoryEntity> getListOfCategories() {
		List<ProductCategoryEntity> productsCategoryList = null;
		try {

			productsCategoryList =	productCategoryDAO.getProductCategory();
			
		} catch (Exception e) {
			throw e;
		}

		return productsCategoryList;
	}

}
