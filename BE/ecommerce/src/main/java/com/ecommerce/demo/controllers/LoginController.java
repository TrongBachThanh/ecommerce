package com.ecommerce.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.request.LoginInputDto;
import com.ecommerce.demo.dto.response.ResponseDto;
import com.ecommerce.demo.services.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/customer/login")
public class LoginController {
    @Autowired
    private AuthService authService;
    @Autowired
    LoginController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping
    public ResponseEntity<ResponseDto> login (@Valid @RequestBody LoginInputDto dto) {
    return authService.login(dto);
    }
}
