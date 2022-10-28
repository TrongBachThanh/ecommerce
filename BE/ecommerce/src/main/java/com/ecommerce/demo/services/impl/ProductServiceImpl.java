package com.ecommerce.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.ProductResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.repositories.CategoryRepository;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	ProductRepository productRepository;
	CategoryRepository categoryRepository;
	ModelMapper modelMapper;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper,
			CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public ProductResponseDto getProductById(Long id) {
		Optional<ProductEntity> productOptinal = productRepository.findById(id);

		if (productOptinal.isEmpty()) {
			throw new ResourceFoundException("Product Not Found With Id " + id);
		}
		ProductEntity product = productOptinal.get();

		getProductByCategoryId(1l);
		return modelMapper.map(product, ProductResponseDto.class);
	}

	@Override
	public ProductResponseDto createProduct(ProductUpdateDto dto) {
		ProductEntity product = modelMapper.map(dto, ProductEntity.class);
		ProductEntity savedProduct = productRepository.save(product);
		return modelMapper.map(savedProduct, ProductResponseDto.class);
	}

	@Override
	public ProductResponseDto updateProduct(ProductUpdateDto dto, Long id) {
		Optional<ProductEntity> productOptinal = productRepository.findById(id);

		if (productOptinal.isEmpty()) {
			throw new ResourceFoundException("Product Not Found With Id " + id);
		}
		ProductEntity product = productOptinal.get();

		dto.setId(id);

		modelMapper.map(dto, product);

		product = productRepository.save(product);

		return modelMapper.map(product, ProductResponseDto.class);
	}

	@Override
	public void deleteProduct(Long id) {

	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		List<ProductEntity> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new ResourceFoundException("Product list is emty");
		}
		List<ProductResponseDto> productdtos = new ArrayList<>();

		for (ProductEntity product : products) {
			ProductResponseDto dto = modelMapper.map(product, ProductResponseDto.class);
			productdtos.add(dto);
		}
		return productdtos;
	}

	@Override
	public List<ProductResponseDto> getProductByCategoryId(Long categoryId) {
		Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryId);

		if (categoryOptional.isEmpty()) {
			throw new ResourceFoundException("Category Not Found With Id " + categoryId);
		}

		CategoryEntity category = categoryOptional.get();

		List<ProductEntity> products = productRepository.findByCategory(category);

		List<ProductResponseDto> productdtos = new ArrayList<>();

		for (ProductEntity product : products) {
			ProductResponseDto dto = modelMapper.map(product, ProductResponseDto.class);
			productdtos.add(dto);
		}
		return productdtos;

	}

	@Override
	public List<ProductResponseDto> getProductByIsNew(boolean isNew) {
		List<ProductEntity> products = productRepository.findByIsNew(true);

		if (products.isEmpty()) {
			throw new ResourceFoundException("Products is empty!");
		}

		List<ProductResponseDto> result = new ArrayList<>();

		for (ProductEntity product : products) {
			result.add(modelMapper.map(product, ProductResponseDto.class));
		}

		return result;
	}

	@Override
	public List<ProductResponseDto> getProductByIsFeatured(boolean isFeatured) {
		List<ProductEntity> products = productRepository.findByIsFeatured(isFeatured);

		if (products.isEmpty()) {
			throw new ResourceFoundException("Products is empty!");
		}

		List<ProductResponseDto> result = new ArrayList<>();

		for (ProductEntity product : products) {
			result.add(modelMapper.map(product, ProductResponseDto.class));
		}

		return result;
	}

}
