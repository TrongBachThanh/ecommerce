package com.ecommerce.demo.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateDto {	
	private Long id;
	private String name;
	private String code;
	private String description;
	private boolean isFeatured;
	private boolean isNew;
	private Double price;
	private String thumbnail;
	private String categoryCode;
	private List<String> images = new ArrayList<>();
	
	
}
