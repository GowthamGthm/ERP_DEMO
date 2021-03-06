package com.obaba.erp.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.obaba.erp.entities.TUser;
import com.obaba.erp.response.JsonResponse;
import com.obaba.erp.serviceImpl.AuthServiceImpl;
import com.obaba.erp.utils.Constants;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	AuthServiceImpl authService;

	@GetMapping(value = Constants.API_LOGIN)
	public Object login(@RequestParam String userName, @RequestParam String password)  {
		
		TUser userAuth = new TUser();
		TUser user = null;

		try {
			Preconditions.checkArgument(!Strings.isNullOrEmpty(userName), "empty UserName");
			Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "empty password");

			userAuth.setUsername(userName);
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
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body("");
		}
	}

	@PostMapping(value = Constants.API_REGISTER)
	public Object register(@RequestBody String input) throws JSONException {

		ObjectMapper  mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		int id;

		TUser userAuth = null;
		try {
			Preconditions.checkArgument(!Strings.isNullOrEmpty(input), "empty body");
			userAuth = mapper.readValue(input,TUser.class);

			Preconditions.checkArgument(!Strings.isNullOrEmpty(userAuth.getUsername()), "empty UserName");
			Preconditions.checkArgument(!Strings.isNullOrEmpty(userAuth.getPassword()), "empty password");

			boolean validEmail = EmailValidator.getInstance().isValid(userAuth.getUsername());

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
		JSONObject json = new JSONObject();
	
		if (id > 0) {
			json.put("success", true);
			json.put("message", "user created successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
		} else {
			json.put("success", false);
			json.put("message", "username already exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json.toString());
		}
	}

	@GetMapping("/test")
	public String test() {
		return "working fine ";
	}

	@PostMapping
	public void addWishListToUser(@RequestBody String input ) {
		try {
			
			
			
			
		}catch (Exception e) {

		}
	}
	
	
}
