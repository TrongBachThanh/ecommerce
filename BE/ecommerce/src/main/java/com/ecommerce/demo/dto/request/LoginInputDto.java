package com.ecommerce.demo.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInputDto {

	@NotBlank(message = "Username is required")
	private String username;

	@Size(min = 3, message = "Password must be at least 6 characters")
	@NotBlank(message = "Password is required")
	private String password;
}
