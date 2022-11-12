package com.ecommerce.demo.controllers.user;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.request.AccountUpdateDto;
import com.ecommerce.demo.dto.response.AccountResponseDto;
import com.ecommerce.demo.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private UserService userService;
	
	public UserController (UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	AccountResponseDto getInformationUserById(@PathVariable("id") Long id) {
		return this.userService.getAccountById(id);
	}

	@PutMapping("/{id}")
	AccountResponseDto updateInformationUser(@PathVariable("id") Long id, @Valid @RequestBody AccountUpdateDto dto) {
		return this.userService.updateAccount(id, dto);
	}
	
	
}
