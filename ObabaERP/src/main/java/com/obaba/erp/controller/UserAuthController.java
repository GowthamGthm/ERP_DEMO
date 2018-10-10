package com.obaba.erp.controller;

import java.util.List;
import java.util.Set;

import org.apache.commons.validator.EmailValidator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.UserAuth;
import com.obaba.erp.response.JsonResponse;
import com.obaba.erp.serviceImpl.AuthServiceImpl;
import com.obaba.erp.serviceImpl.ProductCategoryServiceImpl;
import com.obaba.erp.utils.Constants;

@Controller
public class UserAuthController {

	@Autowired
	AuthServiceImpl authService;

	@Autowired
	ProductCategoryServiceImpl productCategoryService;

	@GetMapping(value = Constants.API_LOGIN)
	public Object login(@RequestParam String userName, @RequestParam String password)  {

		int id;

		UserAuth userAuth = new UserAuth();
		UserAuth user = null;

		try {
			Preconditions.checkArgument(!Strings.isNullOrEmpty(userName), "empty UserName");
			Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "empty password");

			userAuth.setUserName(userName);
			userAuth.setPassword(password);
			user = authService.checkAuth(userAuth);

		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new JsonResponse(true, "login success"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new JsonResponse(false, "username or password doesnt match"));
		}
	}

	@PostMapping(value = Constants.API_REGISTER)
	public Object register(@RequestBody String input) {

		/*
		 * ObjectMapper mapper = new ObjectMapper();
		 * mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 * mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		 * mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS , false);
		 */

		Gson gson = new Gson();

		int id;

		UserAuth userAuth = null;
		try {
			Preconditions.checkArgument(!Strings.isNullOrEmpty(input), "empty body");
			userAuth = gson.fromJson(input, UserAuth.class);

			Preconditions.checkArgument(!Strings.isNullOrEmpty(userAuth.userName), "empty UserName");
			Preconditions.checkArgument(!Strings.isNullOrEmpty(userAuth.password), "empty password");

			boolean validEmail = EmailValidator.getInstance().isValid(userAuth.userName);

			if (!validEmail)
				throw new IllegalArgumentException("invalid email");

			id = authService.insertUser(userAuth);

		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		if (id > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body("user created successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username already exist");
		}

	}

	@ResponseBody
	@GetMapping(value = Constants.API_GET_CATEGORY)
	public Object getCategories() throws JSONException {

		List<TProductCategory> tProductCategories = null;

		try {
			tProductCategories = productCategoryService.getListOfCategories();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String result =  gson.toJson(tProductCategories);
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@GetMapping("/test")
	public String test() {
		return "working fine ";
	}

}
