package com.ecommerce.demo.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;

import com.ecommerce.demo.data.entities.BrandEntity;
import com.ecommerce.demo.data.repositories.BrandRepository;
import com.ecommerce.demo.dto.request.BrandUpdateDto;
import com.ecommerce.demo.dto.response.BrandResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.services.BrandService;

public class BrandServiceImpl implements BrandService {
	private BrandRepository brandRepository;
	private ModelMapper modelMapper;

	@Override
	public BrandResponseDto getBrandById(Long id) {
		Optional<BrandEntity> brandOptinal = brandRepository.findById(id);
		if (brandOptinal.isEmpty()) {
			throw new ResourceFoundException("Brand Not Found");
		}
		BrandEntity brand = brandOptinal.get();

		return modelMapper.map(brand, BrandResponseDto.class);
	}

	@Override
	public BrandResponseDto createBrand(BrandUpdateDto dto) {
		BrandEntity brand = modelMapper.map(dto, BrandEntity.class);
		BrandEntity savedBrand = brandRepository.save(brand);

		return modelMapper.map(savedBrand, BrandResponseDto.class);
	}

	@Override
	public BrandResponseDto updateBrand(BrandUpdateDto dto, Long id) {
		Optional<BrandEntity> brandOptinal = brandRepository.findById(id);

		if (brandOptinal.isEmpty()) {
			throw new ResourceFoundException("Brand Not Found");
		}

		BrandEntity brand = brandOptinal.get();
		modelMapper.map(dto, brand);

		brand = brandRepository.save(brand);

		return modelMapper.map(brand, BrandResponseDto.class);
	}

	@Override
	public void deleteBrand(Long id) {

	}

	public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
		this.brandRepository = brandRepository;
		this.modelMapper = modelMapper;
	}

}
