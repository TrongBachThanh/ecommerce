package com.ecommerce.demo.mappers;

import org.springframework.stereotype.Component;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.dto.response.category.CategoryResponseDto;

@Component
public class CategoryMapper {
	CategoryResponseDto toCategoryResponseDto(CategoryEntity category) {
		CategoryResponseDto dto = new CategoryResponseDto();
		dto.setId(category.getId());
		dto.setCode(category.getCode());
		dto.setName(category.getName());
		return dto;
	}
}
