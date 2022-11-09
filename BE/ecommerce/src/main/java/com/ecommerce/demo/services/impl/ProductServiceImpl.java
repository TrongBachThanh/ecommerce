package com.ecommerce.demo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.dto.response.product.ListProductWithPaginateResponseDto;
import com.ecommerce.demo.dto.response.product.ProductDetailResponseDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.mappers.ProductMapper;
import com.ecommerce.demo.repositories.CategoryRepository;
import com.ecommerce.demo.repositories.ProductRepository;
import com.ecommerce.demo.services.ProductService;
import com.ecommerce.demo.utils.PageableUtil;

@Service
public class ProductServiceImpl implements ProductService {

	ProductRepository productRepository;
	CategoryRepository categoryRepository;
	ModelMapper modelMapper;
	ProductMapper productMapper;
	PageableUtil pageableUtil;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper,
			CategoryRepository categoryRepository, ProductMapper productMapper, PageableUtil pageableUtil) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.categoryRepository = categoryRepository;
		this.productMapper = productMapper;
		this.pageableUtil = pageableUtil;
	}

	@Override
	public ProductDetailResponseDto getProductByCode(String code) {
		Optional<ProductEntity> productOptinal = productRepository.findByCode(code);

		if (productOptinal.isEmpty()) {
			throw new ResourceFoundException("Product Not Found");
		}
		ProductEntity product = productOptinal.get();

		return productMapper.toProductDetailResponseDto(product);
	}

	@Override
	public List<ProductResponseDto> getTopFiveProductByIsNew(Integer offset, Integer limit, String sortBase,
			String sortType) {
		Pageable pageable = pageableUtil.getPageable(offset, limit, sortBase, sortType);

		Page<ProductEntity> productPage = productRepository.findAllByIsNewTrue(pageable);

		if (productPage.isEmpty()) {
			throw new ResourceFoundException("Products is empty!");
		}

		return productPage.stream().map(productMapper::toProductResponseDto).collect(Collectors.toList());
	}

	@Override
	public List<ProductResponseDto> getTopFiveProductByIsFeatured(Integer offset, Integer limit, String sortBase,
			String sortType) {
		Pageable pageable = pageableUtil.getPageable(offset, limit, sortBase, sortType);

		Page<ProductEntity> productPage = productRepository.findAllByIsFeaturedTrue(pageable);

		if (productPage.isEmpty()) {
			throw new ResourceFoundException("Products is empty!");
		}

		return productPage.stream().map(productMapper::toProductResponseDto).collect(Collectors.toList());
	}

	@Override
	public ListProductWithPaginateResponseDto getAllProductsWithPaginateAndSort(Integer offset, Integer limit,
			String sortBase, String sortType) {
		Pageable pageable = pageableUtil.getPageable(offset, limit, sortBase, sortType);

		Page<ProductEntity> productPage = productRepository.findAll(pageable);

		List<ProductResponseDto> productResponseDtoList = productPage.stream().map(productMapper::toProductResponseDto)
				.collect(Collectors.toList());

		ListProductWithPaginateResponseDto listProductWithPaginateResponse = ListProductWithPaginateResponseDto
				.builder().products(productResponseDtoList).totalRow(productPage.getTotalElements())
				.totalPage(productPage.getTotalPages()).build();

		return listProductWithPaginateResponse;
	}

	@Override
	public ListProductWithPaginateResponseDto getAllProductsByCategoryCodeWithPaginateAndSort(String categoryCode,
			Integer offset, Integer limit, String sortBase, String sortType) {

		Optional<CategoryEntity> categoryOption = categoryRepository.findByCodeAndIsDeletedFalse(categoryCode);

		if (categoryCode.isEmpty()) {
			throw new ResourceFoundException("Category Not Found");
		}

		Pageable pageable = pageableUtil.getPageable(offset, limit, sortBase, sortType);

		Page<ProductEntity> productPage = productRepository
				.findAllByIsDeletedFalseAndCategory_Id(categoryOption.get().getId(), pageable);

		List<ProductResponseDto> productResponseDtoList = productPage.stream().map(productMapper::toProductResponseDto)
				.collect(Collectors.toList());

		ListProductWithPaginateResponseDto listProductWithPaginateResponse = ListProductWithPaginateResponseDto
				.builder().products(productResponseDtoList).totalRow(productPage.getTotalElements())
				.totalPage(productPage.getTotalPages()).build();

		return listProductWithPaginateResponse;
	}

	@Override
	public ListProductWithPaginateResponseDto getAllProductsSearchByNameWithPaginate(String query, Integer offset,
			Integer limit) {
		Page<ProductEntity> productPage = productRepository
				.findAllByIsDeletedFalseAndNameLikeIgnoreCase("%" + query + "%", PageRequest.of(offset, limit));

		List<ProductResponseDto> productResponseDtoList = productPage.stream().map(productMapper::toProductResponseDto)
				.collect(Collectors.toList());

		ListProductWithPaginateResponseDto listProductWithPaginateResponse = ListProductWithPaginateResponseDto
				.builder().products(productResponseDtoList).totalRow(productPage.getTotalElements())
				.totalPage(productPage.getTotalPages()).build();
		return listProductWithPaginateResponse;
	}

}
