package com.ecommerce.demo.dto.response.product;

import com.ecommerce.demo.data.entities.CategoryEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponseDto {
	private Long id;
	private String name;
	private String code;
	private String description;
	private boolean isFeatured;
	private boolean isNew;
	private Double price;
	private String thumbnail;
	private CategoryEntity category;
}
