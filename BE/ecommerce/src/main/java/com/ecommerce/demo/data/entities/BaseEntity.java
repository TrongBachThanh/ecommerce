package com.ecommerce.demo.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @GenericGenerator(name = "seq", strategy="increment")
	private Long id;
	
	@CreatedDate
	@Column(name = "created_date")
	private Date createdDate;
	
	@LastModifiedDate
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;
	
	@LastModifiedBy
	@Column(name = "modified_by")
	private String modifiedBy;

}
