package com.ecommerce.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.data.entities.AccountEntity;
import com.ecommerce.demo.dto.request.AccountUpdateDto;
import com.ecommerce.demo.dto.response.AccountResponseDto;
import com.ecommerce.demo.services.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

	private AccountService accountService;

	@GetMapping
	List<AccountEntity> getAcounts() {
		return this.accountService.getAllAccounts();
	}

	@GetMapping("/{id}")
	AccountResponseDto getAccountById(@PathVariable("id") Long id) {
		return this.accountService.getAccountById(id);
	}

	@PostMapping
	AccountResponseDto createAccount(@Valid @RequestBody AccountUpdateDto dto) {
		return this.accountService.createAccount(dto);
	}

	@PutMapping("/{id}")
	AccountResponseDto updateAccount(@PathVariable("id") Long id, @Valid @RequestBody AccountUpdateDto dto) {
		return this.accountService.updateAccount(id, dto);
	}

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
}
