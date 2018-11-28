package com.obaba.erp.entities;
// Generated 25 Nov, 2018 1:41:19 AM by Hibernate Tools 5.1.7.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * TProductCategory generated by hbm2java
 */
@Entity
@Table(name = "t_product_category", catalog = "demo", uniqueConstraints = @UniqueConstraint(columnNames = "category_name"))
public class TProductCategory implements java.io.Serializable {

	private Integer categoryId;
	private String categoryName;
	private String categoryDesc;
	private Date createDate;
	private Integer createdBy;
	private Date updateDate;
	private Integer updatedBy;
	private Set<TProductSubCategory> TProductSubCategories = new HashSet<TProductSubCategory>(0);

	public TProductCategory() {
	}

	public TProductCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	public TProductCategory(String categoryName, String categoryDesc, Date createDate, Integer createdBy,
			Date updateDate, Integer updatedBy, 
			Set<TProductSubCategory> TProductSubCategories) {
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.updateDate = updateDate;
		this.updatedBy = updatedBy;
		this.TProductSubCategories = TProductSubCategories;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "category_id", unique = true, nullable = false)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_name", unique = true, nullable = false)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "category_desc")
	public String getCategoryDesc() {
		return this.categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "updated_by")
	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "TProductCategory")
	@Fetch (FetchMode.SELECT)
	public Set<TProductSubCategory> getTProductSubCategories() {
		return this.TProductSubCategories;
	}

	public void setTProductSubCategories(Set<TProductSubCategory> TProductSubCategories) {
		this.TProductSubCategories = TProductSubCategories;
	}

}
