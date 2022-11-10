package com.ecommerce.demo.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.RatingEntity;
import com.ecommerce.demo.data.repositories.RatingRepository;
import com.ecommerce.demo.dto.request.RatingUpdateDto;
import com.ecommerce.demo.dto.response.RatingResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	RatingRepository ratingRepository;
	ModelMapper modelMapper;

	@Autowired
	public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper) {
		this.ratingRepository = ratingRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public RatingResponseDto getRatingById(Long id) {
		Optional<RatingEntity> ratingOptional = ratingRepository.findById(id);
		if (ratingOptional.isEmpty()) {
			throw new ResourceFoundException("Rating Not Found");
		}
		RatingEntity rating = ratingOptional.get();

		return modelMapper.map(rating, RatingResponseDto.class);
	}

	@Override
	public RatingResponseDto createRating(RatingUpdateDto dto) {
		RatingEntity rating = modelMapper.map(dto, RatingEntity.class);
		RatingEntity savedRating = ratingRepository.save(rating);
		return modelMapper.map(savedRating, RatingResponseDto.class);
	}

	@Override
	public RatingResponseDto updateRating(RatingUpdateDto dto, Long id) {
		Optional<RatingEntity> ratingOptional = ratingRepository.findById(id);
		if (ratingOptional.isEmpty()) {
			throw new ResourceFoundException("Rating Not Found");
		}
		RatingEntity rating = ratingOptional.get();
		
		dto.setId(id);
		
		modelMapper.map(dto, rating);

		rating = ratingRepository.save(rating);

		return modelMapper.map(rating, RatingResponseDto.class);
	}

}
