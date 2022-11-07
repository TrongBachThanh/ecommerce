package com.ecommerce.demo.dto.response.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDto {
	private Long id;
	private String name;
	private String code;
}
