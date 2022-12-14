package com.ecommerce.demo.dto.response;

import com.ecommerce.demo.dto.response.category.CategoryResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDto {
	private Long id;
	private String name;
	private String code;
	private String description;
	private boolean isFeatured;
	private boolean isNew;
	private Double price;
	private String thumbnail;
	CategoryResponseDto category;
}
