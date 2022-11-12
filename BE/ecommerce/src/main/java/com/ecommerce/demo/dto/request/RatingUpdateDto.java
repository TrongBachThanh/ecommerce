package com.ecommerce.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingUpdateDto {
	private Long id;
	private String comment;
	private Integer score;
	private Long userId;
	private Long productId;
}
