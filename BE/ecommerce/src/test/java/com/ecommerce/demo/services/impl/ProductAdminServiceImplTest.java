package com.ecommerce.demo.services.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.data.repositories.CategoryRepository;
import com.ecommerce.demo.data.repositories.ProductRepository;
import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;
import com.ecommerce.demo.exceptions.ItemExistException;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.mappers.ProductMapper;
import com.ecommerce.demo.services.ProductImageService;

public class ProductAdminServiceImplTest {
	ProductRepository productRepository;
	CategoryRepository categoryRepository;
	ProductImageService productImageService;

	ProductMapper productMapper;
	ModelMapper modelMapper;

	ProductAdminServiceImpl productAdminServiceImpl;

	@BeforeEach
	void beforeEach() {
		productRepository = mock(ProductRepository.class);
		categoryRepository = mock(CategoryRepository.class);
		productImageService = mock(ProductImageService.class);

		productMapper = mock(ProductMapper.class);
		modelMapper = mock(ModelMapper.class);

		productAdminServiceImpl = new ProductAdminServiceImpl(productRepository, categoryRepository, productMapper,
				modelMapper, productImageService);
	}

	@Test
	void createProduct_ShouldReturnProductResponseDto_WhenDataValid() {
		
		ProductUpdateDto dto = new ProductUpdateDto();
		dto.setCategoryCode("123");
		
		dto.setImages(List.of("image"));
		
		dto.setName("name");
		
		
//		ProductUpdateDto dto = mock(ProductUpdateDto.class);
		
//		ProductUpdateDto dto = ProductUpdateDto.bui
		ProductEntity product = mock(ProductEntity.class);
		
		
		
		ProductEntity savedProduct = mock(ProductEntity.class);
		CategoryEntity category = mock(CategoryEntity.class);

		ProductResponseDto expected = mock(ProductResponseDto.class);

		when(productRepository.findByName("name")).thenReturn(Optional.empty());
		when(categoryRepository.findByCode("123")).thenReturn(Optional.of(category));

		when(productMapper.toProductEntity(dto)).thenReturn(product);
		
		when(productRepository.save(product)).thenReturn(savedProduct);

//		when(dto.getImages().isEmpty()).thenReturn(false);

//		when(dto.getImages()).thenReturn(List.of("image"));

		when(modelMapper.map(savedProduct, ProductResponseDto.class)).thenReturn(expected);


		ProductResponseDto actual = productAdminServiceImpl.createProduct(dto);
		
		
		verify(productImageService).createProductImages(dto.getImages(), "name");

		assertThat(expected, is(actual));
	}

	@Test
	void createProduct_ShouldThrowItemExistException_WhenProductNameExist() {
		ProductUpdateDto dto = mock(ProductUpdateDto.class);
		ProductEntity product = mock(ProductEntity.class);
		when(productRepository.findByName(dto.getName())).thenReturn(Optional.of(product));

		ItemExistException actualException = Assertions.assertThrows(ItemExistException.class, () -> {
			productAdminServiceImpl.createProduct(dto);
		});

		Assertions.assertEquals("Product Name has exist", actualException.getMessage());
	}

	@Test
	void createProduct_ShouldThrowResourceFoundException_WhenCatagoryNameNotFound() {
		ProductUpdateDto dto = mock(ProductUpdateDto.class);

		when(categoryRepository.findByCode(dto.getCategoryCode())).thenReturn(Optional.empty());

		ResourceFoundException actualException = Assertions.assertThrows(ResourceFoundException.class, () -> {
			productAdminServiceImpl.createProduct(dto);
		});

		Assertions.assertEquals("Category Not Found", actualException.getMessage());
	}

	@Test
	void updateProduct_ShouldReturnProductResponseDto_WhenDataValid() {
		ProductUpdateDto dto = mock(ProductUpdateDto.class);
		ProductEntity product = mock(ProductEntity.class);
		ProductEntity savedProduct = mock(ProductEntity.class);
		CategoryEntity category = mock(CategoryEntity.class);

		ProductResponseDto expected = mock(ProductResponseDto.class);

		when(productRepository.findById(2l)).thenReturn(Optional.of(product));

		when(categoryRepository.findByCode(dto.getCategoryCode())).thenReturn(Optional.of(category));

		when(productRepository.save(product)).thenReturn(savedProduct);

		when(modelMapper.map(savedProduct, ProductResponseDto.class)).thenReturn(expected);

		ProductResponseDto actual = productAdminServiceImpl.updateProduct(dto, 2l);

		verify(product).setName(dto.getName());
		verify(product).setDescription(dto.getDescription());
		verify(product).setCode(dto.getCode());
		verify(product).setNew(dto.isNew());
		verify(product).setFeatured(dto.isFeatured());
		verify(product).setThumbnail(dto.getThumbnail());
		verify(product).setPrice(dto.getPrice());
		verify(product).setCategory(category);

		assertThat(expected, is(actual));
	}

	@Test
	void updateProduct_ShouldThrowResourceFoundException_WhenProductIdInvalid() {
		ProductUpdateDto dto = mock(ProductUpdateDto.class);

		when(productRepository.findById(2l)).thenReturn(Optional.empty());

		ResourceFoundException actualException = Assertions.assertThrows(ResourceFoundException.class, () -> {
			productAdminServiceImpl.updateProduct(dto, 2l);
		});

		Assertions.assertEquals("Product Not Found", actualException.getMessage());
	}

	@Test
	void updateProduct_ShouldThrowResourceFoundException_WhenCatagoryCodeNotFound() {
		ProductUpdateDto dto = mock(ProductUpdateDto.class);
		ProductEntity product = mock(ProductEntity.class);

		when(productRepository.findById(2l)).thenReturn(Optional.of(product));
		when(categoryRepository.findByCode(dto.getCategoryCode())).thenReturn(Optional.empty());

		ResourceFoundException actualException = Assertions.assertThrows(ResourceFoundException.class, () -> {
			productAdminServiceImpl.updateProduct(dto, 2l);
		});

		Assertions.assertEquals("Category Not Found", actualException.getMessage());
	}

}
