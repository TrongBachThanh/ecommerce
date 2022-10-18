package com.ecommerce.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.data.entities.AccountEntity;
import com.ecommerce.demo.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	private AccountService accountService;
	
	@GetMapping
	List<AccountEntity> getAcounts() {
		return this.accountService.getAllAccounts();
	}
    @GetMapping("/{id}")
    AccountEntity getAccountById(@PathVariable("id") Long id) {
    	return this.accountService.getAccountById(id);
    }

	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
}
