package com.ecommerce.demo.controllers.admin;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ecommerce.demo.dto.request.CategoryUpdateDto;
import com.ecommerce.demo.dto.response.CategoryResponseDto;
import com.ecommerce.demo.securities.JwtEntryPoint;
import com.ecommerce.demo.securities.JwtTokenProvider;
import com.ecommerce.demo.services.AuthService;
import com.ecommerce.demo.services.impl.CategoryAdminServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = CategoryAdminController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })

public class CategoryAdminControllerTest {
	@MockBean
	private CategoryAdminServiceImpl categoryAdminServiceImpl;

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

	private CategoryResponseDto categoryResponseDto;

	@BeforeEach
	void beforeEach() {
		categoryResponseDto = new CategoryResponseDto();
		categoryResponseDto.setName("name");
		categoryResponseDto.setCode("code");
		categoryResponseDto.setId(1l);

	}

	@Test
	void createCategory_ShouldReturnCategoryResponseDto() throws Exception {
		CategoryUpdateDto categoryUpdateDto = new CategoryUpdateDto();

		categoryUpdateDto.setName("name code");

		when(categoryAdminServiceImpl.createCategory(categoryUpdateDto)).thenReturn(categoryResponseDto);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/admin/categories")
				.content(objectMapper.writeValueAsString(categoryUpdateDto)).contentType(MediaType.APPLICATION_JSON);

		MockHttpServletResponse actual = mockMvc.perform(request).andReturn().getResponse();

		assertThat(actual.getContentAsString(),
				is("{\"code\":0," + "\"data\":{\"id\":1,\"name\":\"name\"}"));

//		assertThat(result.getResponse().getContentAsString(), is(objectMapper.writeValueAsString(expected)));

	}

}
