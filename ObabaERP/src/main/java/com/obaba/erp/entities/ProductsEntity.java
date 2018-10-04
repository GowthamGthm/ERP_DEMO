package com.obaba.erp.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Table(name = "t_products")
@Entity
@Data
public class ProductsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	public int productID;

	@Column(name = "product_category_id")
	public int productCategoryID;

	@Column(name = "product_sub_category_id")
	public int productSubCategoryID;
	
	@Column(name = "product_sku")
	public String sku;

	@Column(name = "product_name")
	public String name;

	@Column(name = "product_brand")
	public String brand;

	@Column(name = "product_price")
	public int price;

	@Column(name = "product_size")
	public String size;

	@Column(name = "product_color")
	public String color;

	@Column(name = "product_image")
	public String imageURL;

	
	@Column(name = "product_quantity")
	public int quantity;

	@Column(name = "is_active")
	public int isActive;

	@CreatedDate
	@Column(name = "create_date")
	private Date createDate;

	@LastModifiedDate
	@Column(name = "update_date")
	private Date updateDate;

	@Column(name = "created_by")
	public int createdBy;

	@Column(name = "updated_by")
	public int updatedBy;
	
//	
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private ProductCategoryEntity TProductCategory;
	
	@ManyToOne
	@JoinColumn(name="sub_category_id")
	private ProductSubCategoryEntity TProductSubCategory;

}
