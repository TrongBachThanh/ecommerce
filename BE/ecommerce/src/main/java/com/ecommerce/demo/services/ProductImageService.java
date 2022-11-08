package com.ecommerce.demo.services;

import java.util.List;

import com.ecommerce.demo.data.entities.ProductImageEntity;
import com.ecommerce.demo.dto.request.ProductImageUpdateDto;
import com.ecommerce.demo.dto.response.ProductResponseDto;

public interface ProductImageService {
	public ProductResponseDto getProductImageById(Long id);

	public List<ProductImageEntity> createProductImages(List<String> imagesUrl, String productName);

	public ProductResponseDto updateProductImage(ProductImageUpdateDto dto);

	public void deleteProductImage(Long id);
}
