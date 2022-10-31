package com.ecommerce.demo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecommerce.demo.dto.request.LoginInputDto;
import com.ecommerce.demo.dto.response.ResponseDto;

public interface AuthService extends UserDetailsService {

	UserDetails loadUserByUsername(String userName);
	
    @SuppressWarnings("rawtypes")
	ResponseEntity<ResponseDto> login(LoginInputDto loginRequest);

}
