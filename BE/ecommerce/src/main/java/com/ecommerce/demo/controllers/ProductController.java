package com.ecommerce.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.ProductResponseDto;
import com.ecommerce.demo.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public List<ProductResponseDto> getAllProducts() {
		return productService.getAllProducts();
	}
	 
	@GetMapping("/{id}")
	public ProductResponseDto getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}
	
	@PostMapping()
	public ProductResponseDto createProduct(@Valid @RequestBody ProductUpdateDto dto) {
		return productService.createProduct(dto);
	}
	
	@PutMapping("/{id}")
	public ProductResponseDto updateProduct(@Valid @RequestBody ProductUpdateDto dto, @PathVariable Long id) {
		return productService.updateProduct(dto, id);
	}
}
