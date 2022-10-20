package com.ecommerce.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.AccountEntity;
import com.ecommerce.demo.dto.request.AccountUpdateDto;
import com.ecommerce.demo.dto.response.AccountResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.repositories.AccountRepository;
import com.ecommerce.demo.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	private ModelMapper modelMapper;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
		this.accountRepository = accountRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<AccountEntity> getAllAccounts() {
		return this.accountRepository.findAll();
	}

	@Override
	public AccountResponseDto getAccountById(Long id) {
		Optional<AccountEntity> accountOptional = this.accountRepository.findById(id);
		if (accountOptional.isEmpty()) {
			throw new IllegalArgumentException("Account Not Found IllegalArgumentException");
		}
		return modelMapper.map(accountOptional, AccountResponseDto.class);
	}

	@Override
	public AccountResponseDto createAccount(AccountUpdateDto dto) {
		AccountEntity account = modelMapper.map(dto, AccountEntity.class);
		AccountEntity savedAccount = accountRepository.save(account);
		return modelMapper.map(savedAccount, AccountResponseDto.class);
	}

	@Override
	public AccountResponseDto updateAccount(Long id, AccountUpdateDto dto) {
		Optional<AccountEntity> accountOptinal = this.accountRepository.findById(id);
		if(accountOptinal.isEmpty()) {
			throw new ResourceFoundException("Account Not Found");
		}
		AccountEntity account = accountOptinal.get();
		modelMapper.map(dto, account);
		account = this.accountRepository.save(account);
		return modelMapper.map(account, AccountResponseDto.class);
	}

	@Override
	public void deleteAccount(Long id) {
		
	}
}
