package com.ecommerce.demo.dto.request;

import javax.validation.constraints.NotBlank;

public class AccountUpdateDto {
	
	@NotBlank(message = "Fullname is required")
	private String fullname;
	
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Phone is required")
	private String phone;
	
//	@NotBlank(message = "Username is required")
	private String username;

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	

}
