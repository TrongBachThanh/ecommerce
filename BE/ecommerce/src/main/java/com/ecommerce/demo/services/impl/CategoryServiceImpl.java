package com.ecommerce.demo.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.dto.request.CategoryUpdateDto;
import com.ecommerce.demo.dto.response.CategoryResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.repositories.CategoryRepository;
import com.ecommerce.demo.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	CategoryRepository categoryRepository;
	ModelMapper modelMapper;

	@Override
	public CategoryResponseDto getCategoryById(Long id) {
		Optional<CategoryEntity> categoryOptinal = categoryRepository.findById(id);
		if(categoryOptinal.isEmpty()) {
			throw new ResourceFoundException("Category Not Found");
		}
		CategoryEntity category = categoryOptinal.get();
		
		return modelMapper.map(category, CategoryResponseDto.class);
	}

	@Override
	public CategoryResponseDto createCategory(CategoryUpdateDto dto) {
		return null;
	}

	@Override
	public CategoryResponseDto updateCategory(CategoryUpdateDto dto) {
		return null;
	}

	@Override
	public void deleCategory(Long id) {

	}
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

}
