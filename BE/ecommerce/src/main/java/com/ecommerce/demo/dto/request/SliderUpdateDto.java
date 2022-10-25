package com.ecommerce.demo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SliderUpdateDto {
	private Long id;
	private String imageLink;
	private String productLink;

}
