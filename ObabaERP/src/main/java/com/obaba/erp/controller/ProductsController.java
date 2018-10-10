package com.obaba.erp.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProducts;
import com.obaba.erp.response.HomePageDataResponse;
import com.obaba.erp.serviceImpl.ProductCategoryServiceImpl;
import com.obaba.erp.utils.Constants;

@RestController
public class ProductsController {
	
	@Autowired
	ProductCategoryServiceImpl productCategoryService;

	@ResponseBody
	@GetMapping(value = Constants.API_GET_HOME_PAGE_DATA)
	public Object getCategories() throws JSONException {

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
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String element = gson.toJson(response);
		
		return ResponseEntity.status(HttpStatus.OK).body(element);
	}
	
	
}
