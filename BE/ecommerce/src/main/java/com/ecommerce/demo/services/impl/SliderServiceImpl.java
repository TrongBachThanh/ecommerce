package com.ecommerce.demo.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.SliderEntity;
import com.ecommerce.demo.data.repositories.SliderRepository;
import com.ecommerce.demo.dto.request.SliderUpdateDto;
import com.ecommerce.demo.dto.response.SliderResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.services.SliderService;

@Service
public class SliderServiceImpl implements SliderService {

	private SliderRepository sliderRepository;
	private ModelMapper modelMapper;

	@Autowired
	public SliderServiceImpl(SliderRepository sliderRepository, ModelMapper modelMapper) {
		this.sliderRepository = sliderRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public SliderResponseDto getSliderById(Long id) {
		Optional<SliderEntity> sliderOptinal = sliderRepository.findById(id);
		if (sliderOptinal.isEmpty()) {
			throw new ResourceFoundException("Slider Not Found");
		}
		SliderEntity slider = sliderOptinal.get();

		return modelMapper.map(slider, SliderResponseDto.class);
	}

	@Override
	public SliderResponseDto createSlider(SliderUpdateDto dto) {
		SliderEntity slider = modelMapper.map(dto, SliderEntity.class);
		SliderEntity savedSlider = sliderRepository.save(slider);
		return modelMapper.map(savedSlider, SliderResponseDto.class);
	}

	@Override
	public SliderResponseDto updateSlider(SliderUpdateDto dto, Long id) {

		Optional<SliderEntity> sliderOptinal = sliderRepository.findById(id);

		if (sliderOptinal.isEmpty()) {
			throw new ResourceFoundException("Slider Not Found");
		}

		SliderEntity slider = sliderOptinal.get();
		dto.setId(id);
		modelMapper.map(dto, slider);
		slider = sliderRepository.save(slider);

		return modelMapper.map(slider, SliderResponseDto.class);
	}

	@Override
	public void deleteSlider(Long id) {
		Optional<SliderEntity> sliderOptinal = sliderRepository.findById(id);

		if (sliderOptinal.isEmpty()) {
			throw new ResourceFoundException("Slider Not Found");
		}
		sliderRepository.deleteById(id);
	}

}
