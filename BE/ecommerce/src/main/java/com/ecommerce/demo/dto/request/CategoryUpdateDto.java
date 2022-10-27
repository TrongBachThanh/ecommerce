package com.ecommerce.demo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryUpdateDto {
	private Long id;
	private String name;
	private String code;

}
