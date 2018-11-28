package com.obaba.erp.service;

import java.util.List;

import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProductSubCategory;
import com.obaba.erp.entities.TProducts;
import com.obaba.erp.entities.TUser;
import com.obaba.erp.entities.TWishlist;
import com.obaba.erp.request.WishListRequest;
import com.obaba.erp.response.UploadFileResponse;

public interface IProductCategoryService {

	public List<TProductCategory> getListOfCategories();

	public List<TProducts> getHomeProducts();

	public List<TProducts> getProductsByID(int productID);
	
	public int addWishList(WishListRequest wishList);

	public TUser getWishListByUser(int userId);

	public int addProductCategory(TProductCategory productCategory);
	
	public List<TProductCategory> getAllProductsCategory();

	public int addProductSubCategory(TProductSubCategory productSubCategories, List<UploadFileResponse> uploadFilesList);

}
