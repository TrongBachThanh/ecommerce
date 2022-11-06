package com.ecommerce.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.request.AccountUpdateDto;
import com.ecommerce.demo.dto.request.LoginInputDto;
import com.ecommerce.demo.dto.response.AccountResponseDto;
import com.ecommerce.demo.dto.response.LoginResponseDto;
import com.ecommerce.demo.services.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class AuthController {
	@Autowired
	private AuthService authService;

	@Autowired
	AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginInputDto dto) {

		return ResponseEntity.status(HttpStatus.OK).body(authService.login(dto));
	}
	
	 @PostMapping("/register")
	 AccountResponseDto createAccount(@Valid @RequestBody AccountUpdateDto dto) {
			return this.authService.createAccount(dto);
		}
	
	
}
