package com.ecommerce.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.response.CategoryResponseDto;
import com.ecommerce.demo.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	List<CategoryResponseDto> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping("/{id}")
	CategoryResponseDto getCategoryById(@PathVariable("id") Long id) {
		return categoryService.getCategoryById(id);
	}

}
