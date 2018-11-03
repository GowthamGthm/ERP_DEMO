package com.obaba.erp.dao;

import java.util.List;

import com.obaba.erp.entities.TUser;
import com.obaba.erp.entities.TWishlist;

public interface IWishListDAO {

	public int  insertWish(TWishlist tWishList);

	public TUser getWishListByUser(int userId);
	
}
