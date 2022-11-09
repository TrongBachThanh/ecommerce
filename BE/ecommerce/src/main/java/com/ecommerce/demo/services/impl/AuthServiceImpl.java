package com.ecommerce.demo.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.constants.ERole;
import com.ecommerce.demo.data.entities.AccountEntity;
import com.ecommerce.demo.dto.request.AccountUpdateDto;
import com.ecommerce.demo.dto.request.LoginInputDto;
import com.ecommerce.demo.dto.response.AccountResponseDto;
import com.ecommerce.demo.dto.response.LoginResponseDto;
import com.ecommerce.demo.exceptions.AuthenticationException;
import com.ecommerce.demo.exceptions.ItemExistException;
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

	@Autowired
	ModelMapper modelMapper;

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

	@Override
	public LoginResponseDto login(LoginInputDto dto) {
		String username = dto.getUsername();
		Optional<AccountEntity> accountOptional = accountRepository.findByUsername(username);

		if (accountOptional.isEmpty()) {
			throw new ResourceFoundException("Account not found");
		}

		AccountEntity account = accountOptional.get();

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(dto.getPassword(), account.getPassword())) {
			throw new AuthenticationException("Password is incorrect");
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				account.getUsername(), account.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);

		UserDetails userDetails = loadUserByUsername(account.getUsername());
		String jwt = jwtTokenProvider.generateJwtToken(userDetails);
		Date expriredDate = jwtTokenProvider.getExpirationDate(jwt);

		LoginResponseDto loginResponseDto = new LoginResponseDto(account.getUsername(), account.getFullName(),
				account.getRoleId(), jwt, expriredDate);

		return loginResponseDto;
	}

	@Override
	public AccountResponseDto createAccount(AccountUpdateDto dto) {

		Optional<AccountEntity> accountOptional = accountRepository.findByUsername(dto.getUsername());

		if (accountOptional.isPresent()) {
			throw new ItemExistException("Username is already signed up");
		}

		AccountEntity account = modelMapper.map(dto, AccountEntity.class);

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		account.setPassword(encoder.encode(account.getPassword()));
		account.setRoleId(ERole.ROLE_USER);

		account.setIsActive(true);

		account.setFullName(String.join(" ", account.getFirstName(), account.getLastName()));

		AccountEntity savedAccount = accountRepository.save(account);

		return modelMapper.map(savedAccount, AccountResponseDto.class);
	}

}
