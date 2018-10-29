package com.obaba.erp.service;

import java.util.List;

import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProducts;
import com.obaba.erp.request.WishListRequest;

public interface IProductCategoryService {

	public List<TProductCategory> getListOfCategories();

	public List<TProducts> getHomeProducts();

	public List<TProducts> getProductsByID(int productID);
	
	public int addWishList(WishListRequest wishList);

}
