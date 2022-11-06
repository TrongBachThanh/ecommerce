package com.ecommerce.demo.dto.response;

import java.util.Date;

import com.ecommerce.demo.constants.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
	private String username;
	private String fullName;
	private ERole role;
	private String token;
	private Date expirationTimestamp;
}
