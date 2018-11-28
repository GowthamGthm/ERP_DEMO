package com.obaba.erp.dao;

import java.util.List;

import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProductSubCategory;
import com.obaba.erp.entities.TProducts;

public interface IProductCategoryDAO {

	public List<TProductCategory> getProductCategory();

	public List<TProducts> getHomeProducts();

	public List<TProducts> getProductsByID(int productID);
	
	public TProducts getProductByID(int productID);
	
	public int  addProductCategory(TProductCategory productCategory);

	public List<TProductCategory> getAllProductCategories();

	public int addProductSubCategory(TProductSubCategory subcategory);

}
