package com.ecommerce.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.data.repositories.CategoryRepository;
import com.ecommerce.demo.dto.request.CategoryUpdateDto;
import com.ecommerce.demo.dto.response.CategoryResponseDto;
import com.ecommerce.demo.exceptions.ItemExistException;
import com.ecommerce.demo.services.CategoryAdminService;

@Service
public class CategoryAdminServiceImpl implements CategoryAdminService {

	CategoryRepository categoryRepository;
	ModelMapper modelMapper;

	public CategoryAdminServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryResponseDto createCategory(CategoryUpdateDto dto) {

		if (categoryRepository.existsByName(dto.getName())) {
			throw new ItemExistException("Category Name Has Exist");
		}

		CategoryEntity category = modelMapper.map(dto, CategoryEntity.class);

		CategoryEntity savedCategory = categoryRepository.save(category);

		return modelMapper.map(savedCategory, CategoryResponseDto.class);
	}

	@Override
	public CategoryResponseDto updateCategory(CategoryUpdateDto dto, Long id) {
		if (categoryRepository.existsByName(dto.getName())) {
			throw new ItemExistException("Category Name Has Exist");
		}

		CategoryEntity category = modelMapper.map(dto, CategoryEntity.class);

		CategoryEntity savedCategory = categoryRepository.save(category);

		return modelMapper.map(savedCategory, CategoryResponseDto.class);
	}

	@Override
	public void deleteCategory(Long id) {

	}

}
