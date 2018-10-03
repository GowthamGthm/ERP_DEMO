package com.obaba.erp.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
@Entity
@Table(name = "t_product_category")
@Data
public class ProductCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	public int categoryID;
	
	@Column(name = "category_name" , unique=true)
	public String categoryName;
	
	@Column(name = "category_desc")
	public String categoryDescription;
	
	@CreatedDate
	@Column(name = "create_date")
	private Date createDate;
	
	@LastModifiedDate
	@Column(name = "update_date")
	private Date updateDate;
	
	@Column(name = "created_by")
	public String createdBy;
	
	@Column(name = "updated_by")
	public String updatedBy;
	
}
