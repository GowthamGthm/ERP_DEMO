package com.obaba.erp.response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.obaba.erp.entities.TProductCategory;
import com.obaba.erp.entities.TProducts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageDataResponse {

	@Expose
	List<TProductCategory> productCategories; 
	@Expose
	List<TProducts> homeProducts ;
	
}
