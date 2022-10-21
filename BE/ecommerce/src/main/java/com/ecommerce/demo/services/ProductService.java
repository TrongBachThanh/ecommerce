package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.ProductResponseDto;

public interface ProductService {
	public ProductResponseDto getProductById(Long id);

	public ProductResponseDto createProduct(ProductUpdateDto dto);

	public ProductResponseDto updateProduct(ProductUpdateDto dto);

	public void deleteProduct(Long id);
}
