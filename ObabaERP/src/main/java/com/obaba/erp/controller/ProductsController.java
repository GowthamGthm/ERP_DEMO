package com.obaba.erp.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProducts;
import com.obaba.erp.request.WishListRequest;
import com.obaba.erp.response.HomePageDataResponse;
import com.obaba.erp.service.IProductCategoryService;
import com.obaba.erp.utils.Constants;

@RestController
@RequestMapping(value="/products")
public class ProductsController {
	
	@Autowired
	IProductCategoryService productCategoryService;

	@ResponseBody
	@GetMapping(value = Constants.API_GET_HOME_PAGE_DATA)
	public Object getHomePageData() throws JSONException {

		List<TProductCategory> tProductCategories = null;
		List<TProducts> tHomeProducts = null;

		try {
			tProductCategories = productCategoryService.getListOfCategories();
			tHomeProducts = productCategoryService.getHomeProducts();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		HomePageDataResponse response = new HomePageDataResponse(tProductCategories,tHomeProducts);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@ResponseBody
	@GetMapping(value = Constants.API_GET_PRODUCTS)
	public Object getProductsByID(@RequestParam int productID)  {
		List<TProducts> tProducts = null;

		try {
			Preconditions.checkArgument(!(productID==0) , "Invalid productID");
			tProducts = productCategoryService.getProductsByID(productID);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if(tProducts == null ) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tProducts);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(tProducts);
		}
	}
	
	
	
	@ResponseBody
	@GetMapping(value = Constants.API_WISH_LIST)
	public Object getWishListByUser(@RequestParam int productID)  {
		List<TProducts> tProducts = null;

		try {
			Preconditions.checkArgument(!(productID==0) , "Invalid productID");
			tProducts = productCategoryService.getProductsByID(productID);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if(tProducts == null ) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tProducts);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(tProducts);
		}
	}
	
	@ResponseBody
	@PostMapping(value = Constants.API_WISH_LIST)
	public Object addWishListByUser(@RequestBody String input)  {
		
		
		ObjectMapper mapper = new ObjectMapper();
		WishListRequest wishrequest = null;
		int id = 0;

		try {
			 Preconditions.checkArgument(!Strings.isNullOrEmpty(input));
			 wishrequest = mapper.readValue(input, WishListRequest.class);
			 id =  productCategoryService.addWishList(wishrequest);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if(id <= 0 ) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("not created");
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body("created");
		}
	}
	
	@ResponseBody
	@DeleteMapping(value = Constants.API_WISH_LIST)
	public Object deleteWishListByUser(@RequestBody int productID)  {
		List<TProducts> tProducts = null;

		try {
			Preconditions.checkArgument(!(productID==0) , "Invalid productID");
			tProducts = productCategoryService.getProductsByID(productID);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		
		if(tProducts == null ) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tProducts);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(tProducts);
		}
	}	
	
}
