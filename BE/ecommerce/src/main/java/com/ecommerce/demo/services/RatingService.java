package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.RatingUpdateDto;
import com.ecommerce.demo.dto.response.RatingResponseDto;

public interface RatingService {
	public RatingResponseDto getRatingById(Long id);

	public RatingResponseDto createRating(RatingUpdateDto dto);

	public RatingResponseDto updateRating(RatingUpdateDto dto, Long id);
}
