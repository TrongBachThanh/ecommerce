package com.ecommerce.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.dto.request.CategoryUpdateDto;
import com.ecommerce.demo.dto.response.CategoryResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.repositories.CategoryRepository;
import com.ecommerce.demo.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	CategoryRepository categoryRepository;
	ModelMapper modelMapper;

	@Override
	public List<CategoryResponseDto> getAllCategories() {
		List<CategoryEntity> categories = categoryRepository.findAll();
		if (categories.isEmpty()) {
			throw new ResourceFoundException("Categorys Not Found");
		}
		List<CategoryResponseDto> categoriesdto = new ArrayList<>();

		for (CategoryEntity category : categories) {
			CategoryResponseDto dto = modelMapper.map(category, CategoryResponseDto.class);
			categoriesdto.add(dto);
		}
		return categoriesdto;
	}

	@Override
	public CategoryResponseDto getCategoryById(Long id) {
		Optional<CategoryEntity> categoryOptinal = categoryRepository.findById(id);
		if (categoryOptinal.isEmpty()) {
			throw new ResourceFoundException("Category Not Found");
		}
		CategoryEntity category = categoryOptinal.get();

		return modelMapper.map(category, CategoryResponseDto.class);
	}

	@Override
	public CategoryResponseDto createCategory(CategoryUpdateDto dto) {
		CategoryEntity category = modelMapper.map(dto, CategoryEntity.class);

		CategoryEntity savedCategory = categoryRepository.save(category);

		return modelMapper.map(savedCategory, CategoryResponseDto.class);
	}

	@Override
	public CategoryResponseDto updateCategory(CategoryUpdateDto dto, Long id) {
		Optional<CategoryEntity> categoryOptinal = categoryRepository.findById(id);
		if (categoryOptinal.isEmpty()) {
			throw new ResourceFoundException("Category Not Found");
		}
		CategoryEntity category = categoryOptinal.get();

		dto.setId(id);

		modelMapper.map(dto, category);
		
		category = categoryRepository.save(category);

		return modelMapper.map(category, CategoryResponseDto.class);
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
