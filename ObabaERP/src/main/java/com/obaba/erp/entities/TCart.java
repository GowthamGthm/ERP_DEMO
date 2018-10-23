package com.obaba.erp.entities;
// Generated 24 Oct, 2018 12:07:49 AM by Hibernate Tools 5.2.10.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_cart", catalog = "demo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TCart implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId", nullable = false)
	private TProducts TProducts;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private TUser TUser;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", length = 19)
	private Date updateDate;
	
	@Column(name = "created_by")
	private Byte createdBy;
	
	@Column(name = "updated_by")
	private Byte updatedBy;
	
	@Column(name = "is_active", length = 55)
	private String isActive;


}
