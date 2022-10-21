package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.ProductImageUpdateDto;
import com.ecommerce.demo.dto.response.ProductResponseDto;

public interface ProductImageService {
	public ProductResponseDto getProductImageById(Long id);

	public ProductResponseDto createProductImage(ProductImageUpdateDto dto);

	public ProductResponseDto updateProductImage(ProductImageUpdateDto dto);

	public void deleteProductImage(Long id);
}
