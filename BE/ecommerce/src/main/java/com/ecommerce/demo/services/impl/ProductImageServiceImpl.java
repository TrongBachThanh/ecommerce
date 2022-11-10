package com.ecommerce.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.data.entities.ProductImageEntity;
import com.ecommerce.demo.data.repositories.ProductImageRepository;
import com.ecommerce.demo.data.repositories.ProductRepository;
import com.ecommerce.demo.dto.request.ProductImageUpdateDto;
import com.ecommerce.demo.dto.response.ProductResponseDto;
import com.ecommerce.demo.services.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductImageRepository productImageRepository;

	@Override
	public ProductResponseDto getProductImageById(Long id) {
		return null;
	}

	@Override
	public ProductResponseDto updateProductImage(ProductImageUpdateDto dto) {
		return null;
	}

	@Override
	public void deleteProductImage(Long id) {

	}

	@Override
	public List<ProductImageEntity> createProductImages(List<String> imagesUrl, String productName) {

		List<ProductImageEntity> images = new ArrayList<ProductImageEntity>();

		ProductEntity product = productRepository.findByName(productName).get();

		for (String url : imagesUrl) {
			ProductImageEntity image = new ProductImageEntity();
			image.setFile(url);
			image.setProduct(product);
			images.add(image);
		}

		List<ProductImageEntity> result = productImageRepository.saveAll(images);

		return result;
	}

}
