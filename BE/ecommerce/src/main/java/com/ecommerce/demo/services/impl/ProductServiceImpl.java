package com.ecommerce.demo.services.impl;

import java.util.ArrayList;
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
import com.ecommerce.demo.data.entities.ProductImageEntity;
import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.product.ListProductWithPaginateResponseDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;
import com.ecommerce.demo.exceptions.ItemExistException;
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
	public ProductResponseDto getProductById(Long id) {
		Optional<ProductEntity> productOptinal = productRepository.findById(id);

		if (productOptinal.isEmpty()) {
			throw new ResourceFoundException("Product Not Found With Id " + id);
		}
		ProductEntity product = productOptinal.get();

		return productMapper.toProductResponseDto(product);
	}

//	@Override
//	public ProductResponseDto createProduct(ProductUpdateDto dto) {
//
//		if (productRepository.findByName(dto.getName()).isPresent()) {
//			throw new ItemExistException("Product Name has exist");
//		}
//
//		Optional<CategoryEntity> categoryOptional = categoryRepository.findByCode(dto.getCategoryCode());
//		if (categoryOptional.isEmpty()) {
//			throw new ResourceFoundException("Category Not Found");
//		}
//		CategoryEntity category = categoryOptional.get();
//
//		ProductEntity product = productMapper.toDtoEntity(dto);
//		product.setCategory(category);
//
//		List<ProductImageEntity> imageList = new ArrayList<>();
//
//		for (String url : dto.getImages()) {
//			ProductImageEntity image = new ProductImageEntity();
//			image.setFile(url);
//			imageList.add(image);
//		}
//
//		product.setProductImages(imageList);
//
//		ProductEntity savedProduct = productRepository.save(product);
//
//		return modelMapper.map(savedProduct, ProductResponseDto.class);
//	}

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

//	@Override
//	public List<ProductResponseDto> getProductByIsFeatured(boolean isFeatured) {
//		List<ProductEntity> products = productRepository.findByIsFeatured(isFeatured);
//
//		if (products.isEmpty()) {
//			throw new ResourceFoundException("Products is empty!");
//		}
//
//		List<ProductResponseDto> result = new ArrayList<>();
//
//		for (ProductEntity product : products) {
//			result.add(modelMapper.map(product, ProductResponseDto.class));
//		}
//
//		return result;
//	}

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
