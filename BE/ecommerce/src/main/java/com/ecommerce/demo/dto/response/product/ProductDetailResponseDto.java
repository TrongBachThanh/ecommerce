package com.ecommerce.demo.dto.response.product;

import java.util.List;

import com.ecommerce.demo.dto.response.ProductImageResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponseDto {
	private Long id;
	private String name;
	private String code;
	private String description;
	private Double price;
	
	private List<ProductImageResponseDto> images;
}
