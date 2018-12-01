package com.obaba.erp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProductSubCategory;
import com.obaba.erp.entities.TProducts;
import com.obaba.erp.entities.TUser;
import com.obaba.erp.entities.TWishlist;
import com.obaba.erp.request.WishListRequest;
import com.obaba.erp.response.HomePageDataResponse;
import com.obaba.erp.response.UploadFileResponse;
import com.obaba.erp.service.IProductCategoryService;
import com.obaba.erp.utils.Constants;
import com.obaba.erp.utils.FileStorageUtil;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

	@Autowired
	IProductCategoryService productCategoryService;

	@Autowired
	FileStorageUtil fileStorage;

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

		HomePageDataResponse response = new HomePageDataResponse(tProductCategories, tHomeProducts);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ResponseBody
	@PostMapping("/addProducts")
	public Object addProducts(@RequestBody TProductCategory productCategory) {
		int id;
		try {
			Preconditions.checkArgument(productCategory != null, "Empty Body ");

			id = productCategoryService.addProductCategory(productCategory);

		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (id > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body("product created successfully ");
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
		}
	}

	@ResponseBody
	@PostMapping("/addProductSubCategory")
	public Object addProductSubCategory(@RequestParam("files") MultipartFile[] files,
			@RequestParam("data") String productSubCategoriesStr) throws Exception {

		List<UploadFileResponse> uploadFilesList = null;
		ObjectMapper mapper = new ObjectMapper();
		//  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);		
		
		int id ;
		try {

			uploadFilesList = Arrays.asList(files).stream().map(file -> fileStorage.uploadFile(file))
					.collect(Collectors.toList());
			
			TProductSubCategory productSubCategory = mapper.readValue(productSubCategoriesStr, TProductSubCategory.class);

			id = productCategoryService.addProductSubCategory(productSubCategory, uploadFilesList);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if (id > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(" Sub product created successfully ");
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
		}
	}

	@ResponseBody
	@GetMapping(value = Constants.API_GET_PRODUCTS)
	public Object getProductsByID(@RequestParam int productID) {
		List<TProducts> tProducts = null;

		try {
			Preconditions.checkArgument(!(productID == 0), "Invalid productID");
			tProducts = productCategoryService.getProductsByID(productID);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (tProducts == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tProducts);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(tProducts);
		}
	}

	@ResponseBody
	@GetMapping(value = Constants.API_WISH_LIST)
	public Object getWishListByUser(@RequestParam int userId) {
		TUser tuser = null;

		try {
			Preconditions.checkArgument(!(userId == 0), "Invalid productID");
			tuser = productCategoryService.getWishListByUser(userId);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (tuser == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tuser);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(tuser);
		}
	}

	@GetMapping(value = "/getProductCategory")
	public Object getAllProductsCategory() {
		List<TProductCategory> categoryList = null;
		try {
			categoryList = productCategoryService.getAllProductsCategory();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (categoryList == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(categoryList);
		}

	}

	@ResponseBody
	@PostMapping(value = Constants.API_WISH_LIST)
	public Object addWishListByUser(@RequestBody String input) {

		ObjectMapper mapper = new ObjectMapper();
		WishListRequest wishrequest = null;
		int id = 0;

		try {
			Preconditions.checkArgument(!Strings.isNullOrEmpty(input));
			wishrequest = mapper.readValue(input, WishListRequest.class);
			id = productCategoryService.addWishList(wishrequest);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (id <= 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("not created");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("created");
		}
	}

	@ResponseBody
	@DeleteMapping(value = Constants.API_WISH_LIST)
	public Object deleteWishListByUser(@RequestBody int productID) {
		List<TProducts> tProducts = null;

		try {
			Preconditions.checkArgument(!(productID == 0), "Invalid productID");
			tProducts = productCategoryService.getProductsByID(productID);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (tProducts == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tProducts);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(tProducts);
		}
	}

}
