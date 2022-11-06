package com.ecommerce.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecommerce.demo.dto.request.AccountUpdateDto;
import com.ecommerce.demo.dto.request.LoginInputDto;
import com.ecommerce.demo.dto.response.AccountResponseDto;
import com.ecommerce.demo.dto.response.LoginResponseDto;

public interface AuthService extends UserDetailsService {

	UserDetails loadUserByUsername(String userName);

	LoginResponseDto login(LoginInputDto loginRequest);
	
	AccountResponseDto createAccount(AccountUpdateDto dto);

}
