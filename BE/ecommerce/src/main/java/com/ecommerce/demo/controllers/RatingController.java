package com.ecommerce.demo.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.request.RatingUpdateDto;
import com.ecommerce.demo.dto.response.RatingResponseDto;
import com.ecommerce.demo.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	RatingService ratingService;
	
	public RatingController(RatingService ratingService) {
		this.ratingService = ratingService;
	}
	
	 @GetMapping("/{id}")
	 RatingResponseDto getRatingById(@PathVariable("id") Long id) {
		 return ratingService.getRatingById(id);
	 }
	 
	 @PostMapping
	 RatingResponseDto createRating(@Valid @RequestBody RatingUpdateDto dto) {
		 return ratingService.createRating(dto);
	 }
	 
	 @PutMapping("/{id}")
	 RatingResponseDto updateRating(@Valid @RequestBody RatingUpdateDto dto, @PathVariable("id") Long id) {
		 return ratingService.updateRating(dto, id);
	 }
}
