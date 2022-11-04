package com.ecommerce.demo.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ecommerce.demo.dto.request.CategoryUpdateDto;
import com.ecommerce.demo.dto.response.CategoryResponseDto;
import com.ecommerce.demo.securities.JwtEntryPoint;
import com.ecommerce.demo.securities.JwtTokenProvider;
import com.ecommerce.demo.services.AuthService;
import com.ecommerce.demo.services.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = CategoryController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
public class CategoryControllerTest {
	@MockBean
	private CategoryServiceImpl categoryService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private AuthService authService;

	@MockBean
	private JwtTokenProvider jwtTokenProvider;

	@MockBean
	private JwtEntryPoint jwtEntryPoint;

	@Test
	void GetAllCategories_ShouldReturnListCategory() throws Exception {
		List<CategoryResponseDto> expectedList = new ArrayList<>();
		CategoryResponseDto category = new CategoryResponseDto();

		expectedList.add(category);

		when(categoryService.getAllCategories()).thenReturn(expectedList);
		MvcResult actual = mockMvc.perform(get("http://localhost:8080/categories")).andReturn();
		assertThat(actual.getResponse().getContentAsString(), is(objectMapper.writeValueAsString(expectedList)));
	}

	@Test
	void GetCategoryById_ShouldReturnCategory() throws Exception {
		CategoryResponseDto expected = new CategoryResponseDto();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/categories/1");

		when(categoryService.getCategoryById(1l)).thenReturn(expected);

		MvcResult actual = mockMvc.perform(requestBuilder).andReturn();

		assertThat(actual.getResponse().getContentAsString(), is(objectMapper.writeValueAsString(expected)));
	}

	@Test
	void CreateCategoryById_ShouldReturnCategory() throws Exception {
		CategoryResponseDto expected = new CategoryResponseDto();
		CategoryUpdateDto dto = new CategoryUpdateDto();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("http://localhost:8080/categories/");

		when(categoryService.createCategory(dto)).thenReturn(expected);
		MvcResult actual = mockMvc.perform(requestBuilder).andReturn();

		assertThat(actual.getResponse().getContentAsString(), is(objectMapper.writeValueAsString(expected)));

	}

}
