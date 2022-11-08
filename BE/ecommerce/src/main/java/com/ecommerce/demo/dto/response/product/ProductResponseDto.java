package com.ecommerce.demo.dto.response.product;

import com.ecommerce.demo.dto.response.category.CategoryResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
	private Long id;
	private String name;
	private String code;
	private String description;
	private boolean isFeatured;
	private boolean isNew;
	private Double price;
	private String thumbnail;
	private CategoryResponseDto category;
}
