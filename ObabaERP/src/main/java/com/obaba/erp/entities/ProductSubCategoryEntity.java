package com.obaba.erp.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Table(name = "t_product_sub_category")
@Entity
@Data
public class ProductSubCategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sub_category_id")
	public int subCategoryID;

//	@Column(name = "category_id")
//	public int categoryID;

	@Column(name = "sub_category_name", unique = true)
	public String subCategoryName;

	@Column(name = "sub_category_desc")
	public String subCategoryDesc;

	@Column(name = "image_url")
	public String imageUrl;

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

	@OneToMany(mappedBy = "TProductSubCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProductsEntity> TProducts;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategoryEntity TProductCategory;

}