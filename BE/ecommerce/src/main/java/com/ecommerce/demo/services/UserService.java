package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.AccountUpdateDto;
import com.ecommerce.demo.dto.response.AccountResponseDto;

public interface UserService {
	public AccountResponseDto updateAccount(Long id, AccountUpdateDto dto);

	public AccountResponseDto getAccountById(Long id);

}
