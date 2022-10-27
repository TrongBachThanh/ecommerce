package com.ecommerce.demo.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RatingResponseDto {
	private Long id;
	private String comment;
	private float score;
}
