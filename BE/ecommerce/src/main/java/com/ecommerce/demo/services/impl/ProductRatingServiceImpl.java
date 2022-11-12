package com.ecommerce.demo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.AccountEntity;
import com.ecommerce.demo.data.entities.ProductEntity;
import com.ecommerce.demo.data.entities.RatingEntity;
import com.ecommerce.demo.data.repositories.AccountRepository;
import com.ecommerce.demo.data.repositories.ProductRatingRepository;
import com.ecommerce.demo.data.repositories.ProductRepository;
import com.ecommerce.demo.dto.request.RatingUpdateDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.mappers.ProductRatingMapper;
import com.ecommerce.demo.services.ProductRatingService;

@Service
public class ProductRatingServiceImpl implements ProductRatingService {
	ProductRatingRepository productRatingRepository;
	AccountRepository accountRepository;
	ProductRepository productRepository;
	ProductRatingMapper productRatingMapper;

	@Autowired
	public ProductRatingServiceImpl(ProductRatingRepository productRatingRepository,
			AccountRepository accountRepository, ProductRepository productRepository,
			ProductRatingMapper productRatingMapper) {
		this.productRatingRepository = productRatingRepository;
		this.accountRepository = accountRepository;
		this.productRepository = productRepository;
		this.productRatingMapper = productRatingMapper;
	}

	@Override
	public void createRatingProduct(RatingUpdateDto dto) {
		Optional<ProductEntity> productOptional = productRepository.findById(dto.getProductId());

		if (productOptional.isEmpty()) {
			throw new ResourceFoundException("Product Not Found");
		}

		Optional<AccountEntity> accountOptional = accountRepository.findById(dto.getUserId());

		if (accountOptional.isEmpty()) {
			throw new ResourceFoundException("Account Not Found");
		}
		
		RatingEntity rating = productRatingMapper.mapUpdateDtoToEntity(dto, productOptional.get(), accountOptional.get());
		
		productRatingRepository.save(rating);
		
		
	}

}
