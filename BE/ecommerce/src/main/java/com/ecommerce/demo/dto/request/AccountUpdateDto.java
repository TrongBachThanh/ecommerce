package com.ecommerce.demo.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountUpdateDto {
	
	@NotBlank(message = "Fullname is required.")
	private String fullname;
	
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Phone is required")
	private String phone;
	
	@NotBlank(message = "Username is required")
	private String username;
	
	@NotBlank(message = "Password is required")
	private String password;
	
	@NotBlank(message = "ConfirmPassword is required")
	private String confirmPassword;
	
	@NotBlank(message = "Address is required")
	private String address;
	
}
