package com.ecommerce.demo.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.data.entities.ProductImageEntity;

@Component
public class ProductImageMapper {

	public ProductImageEntity mapImageProductDtoToEntity(String url, ProductEntity product) {
		ProductImageEntity result = new ProductImageEntity();
		result.setProduct(product);
		result.setFile(url);
		return result;
	}

	public List<ProductImageEntity> mapImagesProductDtoToEntity(List<String> imagesValue, ProductEntity product) {
		return imagesValue.stream().map(image -> mapImageProductDtoToEntity(image, product))
				.collect(Collectors.toList());
	}
}
