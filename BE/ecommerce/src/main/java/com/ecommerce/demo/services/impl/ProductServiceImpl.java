package com.ecommerce.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.ProductResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	ProductRepository productRepository;
	ModelMapper modelMapper;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ProductResponseDto getProductById(Long id) {
		Optional<ProductEntity> productOptinal = productRepository.findById(id);

		if (productOptinal.isEmpty()) {
			throw new ResourceFoundException("Product Not Found With Id " + id);
		}
		ProductEntity product = productOptinal.get();

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

}
