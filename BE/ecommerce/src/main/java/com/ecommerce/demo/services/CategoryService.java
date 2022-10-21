package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.CategoryUpdateDto;
import com.ecommerce.demo.dto.response.CategoryResponseDto;

public interface CategoryService {
	public CategoryResponseDto getCategoryById(Long id);

	public CategoryResponseDto createCategory(CategoryUpdateDto dto);

	public CategoryResponseDto updateCategory(CategoryUpdateDto dto);

	public void deleCategory(Long id);
}
