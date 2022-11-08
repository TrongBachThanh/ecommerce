package com.ecommerce.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.data.entities.ProductImageEntity;
import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;
import com.ecommerce.demo.exceptions.ItemExistException;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.mappers.ProductMapper;
import com.ecommerce.demo.repositories.CategoryRepository;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.services.ProductAdminService;

@Service
public class ProductAdminServiceImpl implements ProductAdminService {
	ProductRepository productRepository;
	CategoryRepository categoryRepository;
	ProductMapper productMapper;
	ModelMapper modelMapper;

	@Autowired
	public ProductAdminServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
			ProductMapper productMapper, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.productMapper = productMapper;
		this.modelMapper = modelMapper;
	}

	@Override
	public ProductResponseDto createProduct(ProductUpdateDto dto) {

		if (productRepository.findByName(dto.getName()).isPresent()) {
			throw new ItemExistException("Product Name has exist");
		}

		Optional<CategoryEntity> categoryOptional = categoryRepository.findByCode(dto.getCategoryCode());
		if (categoryOptional.isEmpty()) {
			throw new ResourceFoundException("Category Not Found");
		}
		CategoryEntity category = categoryOptional.get();

		ProductEntity product = productMapper.toDtoEntity(dto);

		product.setCategory(category);

		List<ProductImageEntity> imageList = new ArrayList<>();

		for (String url : dto.getImages()) {
			ProductImageEntity image = new ProductImageEntity();
			image.setFile(url);
			imageList.add(image);
		}

		product.setProductImages(imageList);

		ProductEntity savedProduct = productRepository.save(product);

		return modelMapper.map(savedProduct, ProductResponseDto.class);
	}

	@Override
	public ProductResponseDto updateProduct(ProductUpdateDto dto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ProductResponseDto updateProduct(ProductUpdateDto dto, Long id) {
//		Optional<ProductEntity> productOptinal = productRepository.findById(id);
//
//		if (productOptinal.isEmpty()) {
//			throw new ResourceFoundException("Product Not Found With Id " + id);
//		}
//		ProductEntity product = productOptinal.get();
//
//		dto.setId(id);
//
//		modelMapper.map(dto, product);
//
//		product = productRepository.save(product);
//
//		return modelMapper.map(product, ProductResponseDto.class);
//	}
//
//	@Override
//	public void deleteProduct(Long id) {
//
//	}

//	@Override
//	public List<ProductResponseDto> getProductByCategoryId(Long categoryId) {
//		Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryId);
//
//		if (categoryOptional.isEmpty()) {
//			throw new ResourceFoundException("Category Not Found With Id " + categoryId);
//		}
//
//		CategoryEntity category = categoryOptional.get();
//
//		List<ProductEntity> products = productRepository.findByCategory(category);
//
//		List<ProductResponseDto> productdtos = new ArrayList<>();
//
//		for (ProductEntity product : products) {
//			ProductResponseDto dto = modelMapper.map(product, ProductResponseDto.class);
//			productdtos.add(dto);
//		}
//		return productdtos;
//
//	}

}
