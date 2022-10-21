package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.SliderUpdateDto;
import com.ecommerce.demo.dto.response.SliderResponseDto;

public interface SliderService {
	public SliderResponseDto getSliderById(Long id);

	public SliderResponseDto createSlider(SliderUpdateDto dto);

	public SliderResponseDto updateSlider(SliderUpdateDto dto);

	public void deleteSlider(Long id);
}
