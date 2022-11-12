package com.ecommerce.demo.mappers;

import org.springframework.stereotype.Component;

import com.ecommerce.demo.data.entities.AccountEntity;
import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.data.entities.RatingEntity;
import com.ecommerce.demo.dto.request.RatingUpdateDto;

@Component
public class ProductRatingMapper {
	
	public RatingEntity mapUpdateDtoToEntity(RatingUpdateDto dto, ProductEntity product, AccountEntity account) {
		return RatingEntity.builder()
				.comment(dto.getComment())
				.score(dto.getScore())
				.account(account)
				.product(product)
				.build();
	}
}
