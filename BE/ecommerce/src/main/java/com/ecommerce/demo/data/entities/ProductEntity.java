package com.ecommerce.demo.data.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;

	@Column(name = "is_featured")
	private boolean isFeatured;

	@Column(name = "is_new")
	private boolean isNew;

	@Column(name = "price")
	private Double price;

	@Column(name = "thumbnail")
	private String thumbnail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private CategoryEntity category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="brand_id")
	private BrandEntity brand;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<ProductImageEntity> productImages;
	
	@OneToMany(mappedBy = "product")
	private List<RatingEntity> ratings;
	
	@OneToMany(mappedBy = "product")
	private List<ColorEntity> colors;
	
}
