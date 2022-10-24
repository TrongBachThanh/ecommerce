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
@Table(name = "rating")
public class RatingEntity extends BaseEntity {
	@Column(name = "comment")
	private String comment;

	@Column(name = "score")
	private float score;

	@ManyToOne()
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	@ManyToOne()
	@JoinColumn(name = "account_id")
	private AccountEntity account;
}
