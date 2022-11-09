package com.ecommerce.demo.services;

import java.util.List;

import com.ecommerce.demo.dto.response.product.ListProductWithPaginateResponseDto;
import com.ecommerce.demo.dto.response.product.ProductDetailResponseDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;

public interface ProductService {

	public ProductDetailResponseDto getProductByCode(String code);

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
