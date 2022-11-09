package com.ecommerce.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.response.product.ListProductWithPaginateResponseDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;
import com.ecommerce.demo.services.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/products")
public class ProductController {
	ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public ProductResponseDto getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@GetMapping("/new-top-5")
	List<ProductResponseDto> getTopFiveNewProduct(
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "offset", defaultValue = "0") Integer offset,
			@RequestParam(name = "sort-base", defaultValue = "id") String sortBase,
			@RequestParam(name = "sort-type", defaultValue = "DESC") String sortType) {
		return productService.getTopFiveProductByIsNew(offset, limit, sortBase, sortType);

	}

	@GetMapping("/featured-top-5")
	List<ProductResponseDto> getTopFiveFeaturedProduct(
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "offset", defaultValue = "0") Integer offset,
			@RequestParam(name = "sort-base", defaultValue = "id") String sortBase,
			@RequestParam(name = "sort-type", defaultValue = "DESC") String sortType) {
		return productService.getTopFiveProductByIsFeatured(offset, limit, sortBase, sortType);
	}

	@GetMapping("/all")
	ListProductWithPaginateResponseDto getAllProductsWithPaginateAndSort(
			@RequestParam(name = "limit", defaultValue = "4") Integer limit,
			@RequestParam(name = "offset", defaultValue = "0") Integer offset,
			@RequestParam(name = "sort-base", defaultValue = "id") String sortBase,
			@RequestParam(name = "sort-type", defaultValue = "DESC") String sortType) {
		return productService.getAllProductsWithPaginateAndSort(offset, limit, sortBase, sortType);
	}

	@GetMapping()
	ListProductWithPaginateResponseDto getAllProductsByCategoryCodeWithPaginateAndSort(
			@RequestParam(name = "category-code") String categoryCode,
			@RequestParam(name = "limit", defaultValue = "4") Integer limit,
			@RequestParam(name = "offset", defaultValue = "0") Integer offset,
			@RequestParam(name = "sort-base", defaultValue = "id") String sortBase,
			@RequestParam(name = "sort-type", defaultValue = "DESC") String sortType) {
		return productService.getAllProductsByCategoryCodeWithPaginateAndSort(categoryCode, offset, limit, sortBase,
				sortType);
	}

}
