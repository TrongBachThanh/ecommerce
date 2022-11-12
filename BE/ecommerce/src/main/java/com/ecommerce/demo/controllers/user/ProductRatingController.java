package com.ecommerce.demo.controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.request.RatingUpdateDto;
import com.ecommerce.demo.services.ProductRatingService;

@RestController
@RequestMapping("/api/v1/ratings")
public class ProductRatingController {
	ProductRatingService productRatingService;

	@Autowired
	public ProductRatingController(ProductRatingService productRatingService) {
		this.productRatingService = productRatingService;
	}

	@PostMapping
	void createProductRating(@Valid @RequestBody RatingUpdateDto dto) {
		productRatingService.createRatingProduct(dto);
	}
}
