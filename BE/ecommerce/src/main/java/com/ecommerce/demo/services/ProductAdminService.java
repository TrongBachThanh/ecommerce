package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;

public interface ProductAdminService {
	public ProductResponseDto createProduct(ProductUpdateDto dto);

	public ProductResponseDto updateProduct(ProductUpdateDto dto, Long id);
	
	
}
