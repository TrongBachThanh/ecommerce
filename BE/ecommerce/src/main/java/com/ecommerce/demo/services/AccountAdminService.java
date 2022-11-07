package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.AccountUpdateDto;
import com.ecommerce.demo.dto.response.AccountResponseDto;

public interface AccountAdminService {
	public AccountResponseDto createAccount(AccountUpdateDto dto);

	public AccountResponseDto updateAccount(Long id, AccountUpdateDto dto);
}
