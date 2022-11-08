package com.ecommerce.demo.controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;
import com.ecommerce.demo.services.ProductAdminService;

@RestController
@RequestMapping("/api/v1/admin/products")
public class ProductAdminController {
	
	@Autowired
	ProductAdminService productAdminService;
	
	@PostMapping
	ProductResponseDto createProduct(@Valid @RequestBody ProductUpdateDto dto) {
		return productAdminService.createProduct(dto);
	}
	
	@PutMapping("/{id}")
	ProductResponseDto updateProduct(@Valid @RequestBody ProductUpdateDto dto, @PathVariable("id") Long id) {
		return productAdminService.updateProduct(dto, id);
	}
}
