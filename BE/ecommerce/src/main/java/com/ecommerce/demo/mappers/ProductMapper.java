package com.ecommerce.demo.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.dto.request.ProductUpdateDto;
import com.ecommerce.demo.dto.response.product.ProductResponseDto;
import com.ecommerce.demo.repositories.CategoryRepository;

@Component
public class ProductMapper {

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductImageMapper productImageMapper;

	public ProductEntity toProductEntity(ProductUpdateDto dto) {
		ProductEntity product = new ProductEntity();

		product.setName(dto.getName());
		product.setCode(dto.getCode());
		product.setPrice(dto.getPrice());

		product.setDescription(dto.getDescription());
		product.setThumbnail(dto.getThumbnail());

		product.setNew(dto.isNew());
		product.setFeatured(dto.isFeatured());
		product.setCategory(categoryRepository.findByCode(dto.getCategoryCode()).get());
		if(!dto.getImages().isEmpty()) {
			product.setProductImages(productImageMapper.mapImagesProductDtoToEntity(dto.getImages(), product));
		}
		return product;
	}

	public ProductResponseDto toProductResponseDto(ProductEntity product) {
		return ProductResponseDto.builder()
				.id(product.getId())
				.code(product.getCode())
				.description(product.getDescription())
				.name(product.getName()).price(product.getPrice())
				.isFeatured(product.isFeatured())
				.isNew(product.isNew())
				.thumbnail(product.getThumbnail())
				.category(categoryMapper.toCategoryResponseDto(product.getCategory())).build();
	}
}
