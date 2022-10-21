package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.BrandUpdateDto;
import com.ecommerce.demo.dto.response.BrandResponseDto;

public interface BrandService {
	public BrandResponseDto getBrandById(Long id);

	public BrandResponseDto createBrand(BrandUpdateDto dto);

	public BrandResponseDto updateBrand(BrandUpdateDto dto, Long id);
	
	public void deleteBrand(Long id);
}
