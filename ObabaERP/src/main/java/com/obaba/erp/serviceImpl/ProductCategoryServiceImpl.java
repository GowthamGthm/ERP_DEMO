package com.obaba.erp.serviceImpl;

import java.util.List;

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
	public List<TProductCategory> getListOfCategories() {
		List<TProductCategory> productsCategoryList = null;
		try {

			productsCategoryList =	productCategoryDaoImpl.getProductCategory();
			
			//productsCategoryList =	productCategoryDaoImpl.findAll();
		//	List<TProductSubCategory> prods= productsCategoryList.get(0).getProductSubCategories();
			System.out.println();
			
		} catch (Exception e) { 
			throw e;
		}

		return productsCategoryList;
	}

}
