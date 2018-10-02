package com.obaba.erp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class JsonResponse {

	private boolean status;
	private String message;
	
}
