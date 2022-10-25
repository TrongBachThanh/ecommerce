package com.ecommerce.demo.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "slider")
public class SliderEntity extends BaseEntity {

	@Column(name = "image_link")
	private String imageLink;

	@Column(name = "product_link")
	private String productLink;

}
