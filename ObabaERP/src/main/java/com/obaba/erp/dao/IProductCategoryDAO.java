package com.obaba.erp.dao;

import java.util.List;

import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProducts;

public interface IProductCategoryDAO {

	public List<TProductCategory> getProductCategory();

	public List<TProducts> getHomeProducts();

	public List<TProducts> getProductByID(int productID);

}
