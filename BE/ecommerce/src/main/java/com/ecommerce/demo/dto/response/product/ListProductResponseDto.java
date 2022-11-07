package com.ecommerce.demo.dto.response.product;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListProductResponseDto {
	private List<ProductResponseDto> products;
}
