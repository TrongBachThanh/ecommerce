package com.ecommerce.demo.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.AccountEntity;
import com.ecommerce.demo.dto.request.LoginInputDto;
import com.ecommerce.demo.dto.response.LoginResponseDto;
import com.ecommerce.demo.dto.response.ResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.repositories.AccountRepository;
import com.ecommerce.demo.securities.JwtTokenProvider;
import com.ecommerce.demo.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		Optional<AccountEntity> accountOptional = accountRepository.findByUsername(userName);

		if (accountOptional.isEmpty()) {
			throw new ResourceFoundException("Account not found");
		}

		AccountEntity account = accountOptional.get();
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(account.getRoleId().name()));

		return new User(account.getUsername(), account.getPassword(), authorities);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity<ResponseDto> login(LoginInputDto dto) {
		String username = dto.getUsername();
		Optional<AccountEntity> accountOptional = accountRepository.findByUsername(username);

		if (accountOptional.isEmpty()) {
			throw new ResourceFoundException("Account not found");
		}

		AccountEntity account = accountOptional.get();

		  PasswordEncoder encoder = new BCryptPasswordEncoder();
	        if (!encoder.matches(dto.getPassword(), account.getPassword())){
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(null, "Invalid Username or password!","400"));
	        }

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				account.getUsername(), account.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		UserDetails userDetails = loadUserByUsername(account.getUsername());
		String jwt = jwtTokenProvider.generateJwtToken(userDetails);
		Date expriredDate = jwtTokenProvider.getExpirationDate(jwt);

		LoginResponseDto loginResponseDto = new LoginResponseDto(account.getUsername(), account.getId(),
				account.getRoleId(), jwt, expriredDate);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(loginResponseDto, "Login Suscess!", "200"));

	}

}
