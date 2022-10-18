package com.ecommerce.demo.services;

import java.util.List;

import com.ecommerce.demo.data.entities.AccountEntity;

public interface AccountService {
	public List<AccountEntity> getAllAccounts();
	public AccountEntity getAccountById(Long id);
}
