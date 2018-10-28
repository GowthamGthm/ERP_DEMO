package com.obaba.erp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obaba.erp.dao.IProductCategoryDAO;
import com.obaba.erp.dao.IUserDAO;
import com.obaba.erp.dao.IWishListDAO;
import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProducts;
import com.obaba.erp.entities.TUser;
import com.obaba.erp.entities.TWishlist;
import com.obaba.erp.request.WishListRequest;
import com.obaba.erp.service.IProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

	@Autowired
	IProductCategoryDAO productCategoryDaoImpl;

	@Autowired
	IUserDAO userDao;
	
	@Autowired
	IWishListDAO wishListDAO;

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

			products = productCategoryDaoImpl.getProductsByID(productID);

		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	@Override
	public int addWishList(WishListRequest wishList) {
		TProducts product = null;
		TUser user = null;
		int id =0 ;

		try {

			product = productCategoryDaoImpl.getProductByID(wishList.getProductId());
			user = userDao.getUserbyID(wishList.getUserId());
			
			if (product != null && user != null) {
				
				TWishlist tWishlist = new TWishlist();
				tWishlist.setTUser(user);
				tWishlist.setTProducts(product);
				
                   id =     wishListDAO.insertWish(tWishlist);
			}

		} catch (Exception e) {
			throw e;
		}
		
		return id ;

	}

}
