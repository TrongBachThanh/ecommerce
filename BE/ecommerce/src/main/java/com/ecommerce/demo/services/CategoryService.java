package com.ecommerce.demo.services;

import java.util.List;

import com.ecommerce.demo.dto.request.CategoryUpdateDto;
import com.ecommerce.demo.dto.response.CategoryResponseDto;

public interface CategoryService {
	public List<CategoryResponseDto> getAllCategories();
	
	public CategoryResponseDto getCategoryById(Long id);

	public CategoryResponseDto createCategory(CategoryUpdateDto dto);

	public CategoryResponseDto updateCategory(CategoryUpdateDto dto, Long id);

	public void deleCategory(Long id);
}
