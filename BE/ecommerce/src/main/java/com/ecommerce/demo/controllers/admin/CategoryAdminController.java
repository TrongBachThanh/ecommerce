package com.ecommerce.demo.controllers.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.request.CategoryUpdateDto;
import com.ecommerce.demo.dto.response.CategoryResponseDto;
import com.ecommerce.demo.services.CategoryAdminService;

@RestController
@RequestMapping("/api/v1/admin/categories")
public class CategoryAdminController {

	private CategoryAdminService categoryAdminService;

	@Autowired
	public CategoryAdminController(CategoryAdminService categoryAdminService) {
		this.categoryAdminService = categoryAdminService;
	}

	@PostMapping
	CategoryResponseDto createCategory(@Valid @RequestBody CategoryUpdateDto dto) {
		return categoryAdminService.createCategory(dto);
	}

	@PutMapping("/{id}")
	CategoryResponseDto updateCategory(@Valid @RequestBody CategoryUpdateDto dto, @PathVariable("id") Long id) {
		return categoryAdminService.updateCategory(dto, id);
	}
}
