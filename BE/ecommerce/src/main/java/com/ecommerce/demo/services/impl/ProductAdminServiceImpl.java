package com.ecommerce.demo.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.data.repositories.CategoryRepository;
import com.ecommerce.demo.data.repositories.ProductRepository;
import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;
import com.ecommerce.demo.exceptions.ItemExistException;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.mappers.ProductMapper;
import com.ecommerce.demo.services.ProductAdminService;
import com.ecommerce.demo.services.ProductImageService;

@Service
public class ProductAdminServiceImpl implements ProductAdminService {
	ProductRepository productRepository;
	CategoryRepository categoryRepository;
	ProductImageService productImageService;

	ProductMapper productMapper;
	ModelMapper modelMapper;

	@Autowired
	public ProductAdminServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
			ProductMapper productMapper, ModelMapper modelMapper, ProductImageService productImageService) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.productMapper = productMapper;
		this.modelMapper = modelMapper;
		this.productImageService = productImageService;
	}

	@Override
	public ProductResponseDto createProduct(ProductUpdateDto dto) {
		Optional<ProductEntity> productOptional = productRepository.findByName(dto.getName());
		if (productOptional.isPresent()) {
			throw new ItemExistException("Product Name has exist");
		}

		Optional<CategoryEntity> categoryOptional = categoryRepository.findByCode(dto.getCategoryCode());
		if (categoryOptional.isEmpty()) {
			throw new ResourceFoundException("Category Not Found");
		}

		ProductEntity product = productMapper.toProductEntity(dto);

		ProductEntity savedProduct = productRepository.save(product);

		if (!dto.getImages().isEmpty()) {
			productImageService.createProductImages(dto.getImages(), dto.getName());
		}
		
		return modelMapper.map(savedProduct, ProductResponseDto.class);
	}

	@Override
	public ProductResponseDto updateProduct(ProductUpdateDto dto, Long id) {

		Optional<ProductEntity> productOptional = productRepository.findById(id);

		if (productOptional.isEmpty()) {
			throw new ResourceFoundException("Product Not Found");
		}

		Optional<CategoryEntity> categoryOptional = categoryRepository.findByCode(dto.getCategoryCode());

		if (categoryOptional.isEmpty()) {
			throw new ResourceFoundException("Category Not Found");
		}

		ProductEntity product = productOptional.get();

		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setCode(dto.getCode());

		product.setNew(dto.isNew());
		product.setFeatured(dto.isFeatured());

		product.setThumbnail(dto.getThumbnail());
		product.setPrice(dto.getPrice());

		product.setCategory(categoryOptional.get());

		ProductEntity savedProduct = productRepository.save(product);

		return modelMapper.map(savedProduct, ProductResponseDto.class);
	}

}
