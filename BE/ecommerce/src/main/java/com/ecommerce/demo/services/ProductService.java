package com.ecommerce.demo.services;

import java.util.List;

import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.ProductResponseDto;

public interface ProductService {
	
	public List<ProductResponseDto> getAllProducts();
	
	public ProductResponseDto getProductById(Long id);

	public ProductResponseDto createProduct(ProductUpdateDto dto);

	public ProductResponseDto updateProduct(ProductUpdateDto dto, Long id);

	public void deleteProduct(Long id);
}
