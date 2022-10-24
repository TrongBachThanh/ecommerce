package com.ecommerce.demo.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "color")
public class ColorEntity extends BaseEntity {
	@Column(name = "code")
	private String code;

	@Column(name = "quantity")
	private int quantity;

	@ManyToOne()
	@JoinColumn(name = "product_id")
	private ProductEntity product;
}
