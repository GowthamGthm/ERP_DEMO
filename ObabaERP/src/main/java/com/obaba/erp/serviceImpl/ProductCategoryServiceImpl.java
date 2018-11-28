package com.obaba.erp.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.obaba.erp.dao.IProductCategoryDAO;
import com.obaba.erp.dao.IUserDAO;
import com.obaba.erp.dao.IWishListDAO;
import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProductSubCategory;
import com.obaba.erp.entities.TProducts;
import com.obaba.erp.entities.TUser;
import com.obaba.erp.entities.TWishlist;
import com.obaba.erp.request.WishListRequest;
import com.obaba.erp.response.UploadFileResponse;
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
		int id = 0;

		try {

			product = productCategoryDaoImpl.getProductByID(wishList.getProductId());
			user = userDao.getUserbyID(wishList.getUserId());

			if (product != null && user != null) {

/*				TWishlist tWishlist = new TWishlist();
				tWishlist.setUserId(user.getId());
				tWishlist.setProductId(product.getProductID());
*/
			//	id = wishListDAO.insertWish(tWishlist);
			}

		} catch (Exception e) {
			throw e;
		}

		return id;

	}

	@Override
	public TUser getWishListByUser(int userId) {

		try {

			wishListDAO.getWishListByUser(userId);

		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	@Override
	public int addProductCategory(TProductCategory productCategory) {

		int id;
		try {

			productCategory.setCreateDate(new Date());
			productCategory.setUpdateDate(new Date());
			productCategory.setCreatedBy(0);
			productCategory.setUpdatedBy(0);

			id = productCategoryDaoImpl.addProductCategory(productCategory);

		} catch (Exception e) {
			throw e;
		}
		return id;
	}

	@Override
	public List<TProductCategory> getAllProductsCategory() {

		List<TProductCategory> categories = null;

		try {

			categories = productCategoryDaoImpl.getAllProductCategories();
		} catch (Exception e) {
			throw e;
		}

		return categories;
	}

	@Override
	public int addProductSubCategory(TProductSubCategory productSubCategories,
			List<UploadFileResponse> uploadFilesList) {

		int id;
		try {

			for (int i = 0; i < uploadFilesList.size(); i++) {
				if (i == 0) {
					productSubCategories.setImageUrl(uploadFilesList.get(i).getFileDownloadUri());
				}
				if (i == 1) {
					productSubCategories.setBannerImageUrl(uploadFilesList.get(i).getFileDownloadUri());
				}
			}

		/*	productSubCategories.setCreateDate(new Date());
			productSubCategories.setUpdateDate(new Date());
			productSubCategories.setCreatedBy(0);
			productSubCategories.setUpdatedBy(0);
*/
			 id = productCategoryDaoImpl.addProductSubCategory(productSubCategories);

		} catch (Exception e) {
			throw e;

		}
		return id;

	}

}
