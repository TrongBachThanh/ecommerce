package com.ecommerce.demo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RatingUpdateDto {
	private Long id;
	private String comment;
	private float score;
}
