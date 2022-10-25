package com.ecommerce.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.request.SliderUpdateDto;
import com.ecommerce.demo.dto.response.SliderResponseDto;
import com.ecommerce.demo.services.SliderService;

@RestController
@RequestMapping("/sliders")
public class SliderController {
	private SliderService sliderService;

	@GetMapping("{id}")
	SliderResponseDto getSliderById(@PathVariable("id") Long id) {
		return sliderService.getSliderById(id);
	}
	
	@PostMapping
	SliderResponseDto createSlider(@Valid @RequestBody SliderUpdateDto dto) {
		return sliderService.createSlider(dto);
	}

	@Autowired
	public SliderController(SliderService sliderService) {
		this.sliderService = sliderService;
	}
}
