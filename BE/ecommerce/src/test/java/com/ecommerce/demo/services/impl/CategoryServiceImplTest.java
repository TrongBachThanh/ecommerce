package com.ecommerce.demo.services.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.modelmapper.ModelMapper;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.dto.request.CategoryUpdateDto;
import com.ecommerce.demo.dto.response.CategoryResponseDto;
import com.ecommerce.demo.exceptions.ItemExistException;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.repositories.CategoryRepository;

public class CategoryServiceImplTest {

	CategoryServiceImpl categoryServiceImpl;

	CategoryRepository categoryRepository;
	ModelMapper modelMapper;
	CategoryEntity category;

	CategoryResponseDto categoryResponseDto;
	CategoryUpdateDto categoryUpdateDto;

	@BeforeEach
	void beforeEach() {
		categoryRepository = mock(CategoryRepository.class);
		modelMapper = mock(ModelMapper.class);
		category = mock(CategoryEntity.class);

		categoryResponseDto = mock(CategoryResponseDto.class);
		categoryUpdateDto = mock(CategoryUpdateDto.class);

		categoryServiceImpl = new CategoryServiceImpl(categoryRepository, modelMapper);

	}

	@Test
	void getAllCategories_ShouldThrowResourceFoundException_WhenFindAllCategoryHasException() {
		List<CategoryEntity> list = new ArrayList<CategoryEntity>();
		when(categoryRepository.findAll()).thenReturn(list);

		ResourceFoundException actualException = Assertions.assertThrows(ResourceFoundException.class,
				() -> categoryServiceImpl.getAllCategories());

		Assertions.assertEquals("Categorys Not Found", actualException.getMessage());

	}

	@Test
	void getAllCategories_ShouldReturnListCategoryResponseDto() {
		List<CategoryEntity> categoryList = new ArrayList<>();
		List<CategoryResponseDto> expectedList = new ArrayList<>();

		categoryList.add(category);

		when(categoryRepository.findAll()).thenReturn(categoryList);

		when(modelMapper.map(category, CategoryResponseDto.class)).thenReturn(categoryResponseDto);

		expectedList.add(categoryResponseDto);

		List<CategoryResponseDto> actual = categoryServiceImpl.getAllCategories();

		assertThat(expectedList, is(actual));
	}

	@Test
	void getCategoryById_ShouldThrowResourceFoundException_WhenFindCategoryHasException() {

		when(categoryRepository.findById(1l)).thenReturn(Optional.empty());

		ResourceFoundException actualException = Assertions.assertThrows(ResourceFoundException.class,
				() -> categoryServiceImpl.getCategoryById(1l));

		Assertions.assertEquals("Category Not Found", actualException.getMessage());

	}

	@Test
	void getCategoryById_ShouldReturnCategoryResponseDto_WhenFindCategoryId() {
		CategoryResponseDto expected = new CategoryResponseDto();
		expected.setId(1l);

		when(categoryRepository.findById(1l)).thenReturn(Optional.of(category));
		when(modelMapper.map(category, CategoryResponseDto.class)).thenReturn(expected);

		CategoryResponseDto actual = categoryServiceImpl.getCategoryById(1l);

		assertThat(expected, is(actual));
	}
	
	@Test
	void createCategory_ShouldThrowItemExistException_WhenNameIsExist() {
		when(categoryRepository.existsByName(categoryUpdateDto.getName())).thenReturn(true);
		
		ItemExistException actual = Assertions.assertThrows(ItemExistException.class, () -> {
			categoryServiceImpl.createCategory(categoryUpdateDto);
		});
		
		Assertions.assertEquals("Category Name Has Exist", actual.getMessage());

	}

	@Test
	void createCategory_ShouldReturnCategoryResponseDto_WhenDataValid() {

		CategoryUpdateDto dto = mock(CategoryUpdateDto.class);
		CategoryResponseDto expected = mock(CategoryResponseDto.class);
		CategoryEntity savedCategory = mock(CategoryEntity.class);

		when(modelMapper.map(dto, CategoryEntity.class)).thenReturn(category);
		when(categoryRepository.save(category)).thenReturn(savedCategory);
		when(modelMapper.map(savedCategory, CategoryResponseDto.class)).thenReturn(expected);

		CategoryResponseDto actual = categoryServiceImpl.createCategory(dto);

		assertThat(expected, is(actual));
	}
	
	

}
