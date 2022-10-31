package com.ecommerce.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.request.AccountUpdateDto;
import com.ecommerce.demo.dto.response.AccountResponseDto;
import com.ecommerce.demo.services.AccountService;

@RestController
@RequestMapping("/customer/register")
public class RegisterController {
	private AccountService accountService;

	@Autowired
	public RegisterController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping
	AccountResponseDto createAccount(@Valid @RequestBody AccountUpdateDto dto) {
		return this.accountService.createAccount(dto);
	}
}
