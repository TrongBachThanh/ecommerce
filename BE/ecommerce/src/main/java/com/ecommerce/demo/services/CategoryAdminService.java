package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.CategoryUpdateDto;
import com.ecommerce.demo.dto.response.CategoryResponseDto;

public interface CategoryAdminService {
	public CategoryResponseDto createCategory(CategoryUpdateDto dto);

	public CategoryResponseDto updateCategory(CategoryUpdateDto dto, Long id);

	public void deleteCategory(Long id);
}
