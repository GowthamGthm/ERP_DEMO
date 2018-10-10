package com.obaba.erp.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obaba.erp.dao.IProductCategoryDAO;
import com.obaba.erp.daoImpl.ProductCategoryDAOImpl;
import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProductSubCategory;
import com.obaba.erp.service.IProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
		
	@Autowired
	IProductCategoryDAO productCategoryDaoImpl ;

	@Override
	public Set<TProductCategory> getListOfCategories() {
		Set<TProductCategory> productsCategoryList = null;
		try {

			productsCategoryList =	productCategoryDaoImpl.getProductCategory();
			
			//productsCategoryList =	productCategoryDaoImpl.findAll();
			//Set<TProductSubCategory> prods= productsCategoryList.get(0).getProductSubCategories();
			System.out.println();
			
		} catch (Exception e) { 
			throw e;
		}

		return productsCategoryList;
	}

}
