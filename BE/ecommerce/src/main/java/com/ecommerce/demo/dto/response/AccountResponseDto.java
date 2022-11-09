package com.ecommerce.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponseDto {
	private Long id;
	private String fullName;
	private String email;
	private String phone;
	private String username;
	
}
