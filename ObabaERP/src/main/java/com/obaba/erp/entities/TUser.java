package com.obaba.erp.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Entity
@Table(name = "t_user")
@Data
public class TUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonInclude
	public int id;

	@JsonInclude
	@Column(name = "username", unique = true)
	public String userName;

	@JsonInclude
	@Column(name = "password")
	public String password;
	
	@Column(name = "is_active")
	public char isActive;

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

	/*@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Set<TWishlist> tWishList ;
	

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(referencedColumnName = "product_id")
	private Set<TProducts> tProducts ;*/
	
	
}
