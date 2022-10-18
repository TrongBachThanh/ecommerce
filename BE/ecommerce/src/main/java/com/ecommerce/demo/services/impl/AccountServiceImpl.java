package com.ecommerce.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.AccountEntity;
import com.ecommerce.demo.repositories.AccountRepository;
import com.ecommerce.demo.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	public AccountServiceImpl(AccountRepository  accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public List<AccountEntity> getAllAccounts() {
		return this.accountRepository.findAll();
	}

	@Override
	public AccountEntity getAccountById(Long id) {
		Optional<AccountEntity> accountOptional = this.accountRepository.findById(id);
		return accountOptional.get();
	}

}
