package com.ecommerce.demo.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.constants.EAccountStatus;
import com.ecommerce.demo.data.entities.AccountEntity;
import com.ecommerce.demo.data.repositories.AccountRepository;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.services.AccountAdminService;

@Service
public class AccountAdminServiceImpl implements AccountAdminService{
	AccountRepository accountRepository;
	
	@Autowired
	public AccountAdminServiceImpl (AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public void lockUserById(Long userId) {
		Optional<AccountEntity> accountOptional = accountRepository.findById(userId);
		
		if(accountOptional.isEmpty()) {
			throw new ResourceFoundException("Account Not Found");
		}
		
		AccountEntity account = accountOptional.get();
		
		account.setStatus(EAccountStatus.BLOCK);
		
		accountRepository.save(account);
	}

	@Override
	public void unlockUserById(Long userId) {
		Optional<AccountEntity> accountOptional = accountRepository.findById(userId);
		
		if(accountOptional.isEmpty()) {
			throw new ResourceFoundException("Account Not Found");
		}
		
		AccountEntity account = accountOptional.get();
		
		account.setStatus(EAccountStatus.ACTIVE);
		
		accountRepository.save(account);
	}


}
