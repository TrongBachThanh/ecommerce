package com.ecommerce.demo.services;

import java.util.List;

import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.product.ListProductWithPaginateResponseDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;

public interface ProductService {

	public ProductResponseDto getProductById(Long id);

//
//	public ProductResponseDto createProduct(ProductUpdateDto dto);
//
//	public ProductResponseDto updateProduct(ProductUpdateDto dto, Long id);
//
//	public void deleteProduct(Long id);
//
//	public List<ProductResponseDto> getProductByCategoryId(Long id);
//
	public List<ProductResponseDto> getTopFiveProductByIsNew(Integer offset, Integer limit, String sortBase,
			String sortType);

	public List<ProductResponseDto> getTopFiveProductByIsFeatured(Integer offset, Integer limit, String sortBase,
			String sortType);

	public ListProductWithPaginateResponseDto getAllProductsByCategoryCodeWithPaginateAndSort(String categoryCode,
			Integer offset, Integer limit, String sortBase, String sortType);

	public ListProductWithPaginateResponseDto getAllProductsWithPaginateAndSort(Integer offset, Integer limit,
			String sortBase, String sortType);

	public ListProductWithPaginateResponseDto getAllProductsSearchByNameWithPaginate(String query, Integer offset,
			Integer limit);

}
