package com.obaba.erp.controller;

import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.obaba.erp.entities.UserAuth;
import com.obaba.erp.response.JsonResponse;
import com.obaba.erp.serviceImpl.AuthServiceImpl;
import com.obaba.erp.utils.Constants;

@Controller
public class UserAuthController {

	@Autowired
	AuthServiceImpl authService;

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

	

	@GetMapping("/test")
	public String test() {
		return "working fine ";
	}

}
