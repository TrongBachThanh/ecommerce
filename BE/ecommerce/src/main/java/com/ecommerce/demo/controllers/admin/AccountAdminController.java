package com.ecommerce.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.services.AccountAdminService;

@RestController
@RequestMapping("api/v1/admin/accounts")
public class AccountAdminController {
	AccountAdminService accountAdminService;
	
	@Autowired
	public AccountAdminController(AccountAdminService accountAdminService) {
		this.accountAdminService = accountAdminService;
	}
	
    @PutMapping("/{userId}/block")
	public void blockAccountById(@PathVariable Long userId) {
		accountAdminService.lockUserById(userId);
	}
    
    @PutMapping("/{userId}/unblock")
   	public void unblockAccountById(@PathVariable Long userId) {
   		accountAdminService.unlockUserById(userId);
   	}
}
