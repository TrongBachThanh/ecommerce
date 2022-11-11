package com.ecommerce.demo.services.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerce.demo.constants.ERole;
import com.ecommerce.demo.data.entities.AccountEntity;
import com.ecommerce.demo.data.repositories.AccountRepository;
import com.ecommerce.demo.dto.request.AccountUpdateDto;
import com.ecommerce.demo.dto.request.LoginInputDto;
import com.ecommerce.demo.dto.response.AccountResponseDto;
import com.ecommerce.demo.dto.response.LoginResponseDto;
import com.ecommerce.demo.exceptions.AuthenticationException;
import com.ecommerce.demo.exceptions.ItemExistException;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.securities.JwtTokenProvider;

public class AuthServiceImplTest {

	private AccountRepository accountRepository;
	private JwtTokenProvider jwtTokenProvider;
	ModelMapper modelMapper;
	AuthServiceImpl authServiceImpl;
	LoginInputDto accountDto;
	LoginResponseDto accountResponseDto;
	AccountEntity account;
	PasswordEncoder passwordEncoder;
	AccountUpdateDto accountUpdateDto;

	@BeforeEach
	void beforeEach() {
		accountRepository = mock(AccountRepository.class);
		jwtTokenProvider = mock(JwtTokenProvider.class);
		modelMapper = mock(ModelMapper.class);
		authServiceImpl = mock(AuthServiceImpl.class);
		accountDto = mock(LoginInputDto.class);
		accountResponseDto = mock(LoginResponseDto.class);
		account = mock(AccountEntity.class);
		accountUpdateDto = mock(AccountUpdateDto.class);
		passwordEncoder = mock(PasswordEncoder.class);
		authServiceImpl = new AuthServiceImpl(accountRepository, jwtTokenProvider, modelMapper, passwordEncoder);
	}

	@Test
	void login_ShouldThrowResourceFoundException_WhenUsernameInvalid() {
		accountDto.setUsername("user");

		when(accountRepository.findByUsername(accountDto.getUsername())).thenReturn(Optional.empty());

		ResourceFoundException actualException = Assertions.assertThrows(ResourceFoundException.class, () -> {
			authServiceImpl.login(accountDto);
		});

		Assertions.assertEquals("Account not found", actualException.getMessage());
	}

	@Test
	void login_ShouldThrowResourceFoundException_WhenPasswordIncorrect() {
		accountDto.setUsername("user");

		when(accountRepository.findByUsername(accountDto.getUsername())).thenReturn(Optional.of(account));

		when(passwordEncoder.matches(accountDto.getPassword(), account.getPassword())).thenReturn(false);

		AuthenticationException actualException = Assertions.assertThrows(AuthenticationException.class, () -> {
			authServiceImpl.login(accountDto);
		});

		Assertions.assertEquals("Username or Password is incorrect", actualException.getMessage());
	}

	@Test
	void createAccount_ShouldThrowResourceFoundException_WhenUsernameExist() {

		when(accountRepository.findByUsername(accountDto.getUsername())).thenReturn(Optional.of(account));

		ItemExistException actualException = Assertions.assertThrows(ItemExistException.class, () -> {
			authServiceImpl.createAccount(accountUpdateDto);
		});

		Assertions.assertEquals("Username is already signed up", actualException.getMessage());
	}

	@Test
	void createAccount_ShouldReturnAccountResponseDto_WhenDataValid() {
		AccountEntity savedAccount = mock(AccountEntity.class);
		AccountResponseDto expected = mock(AccountResponseDto.class);

		when(accountRepository.findByUsername(accountDto.getUsername())).thenReturn(Optional.empty());
		when(modelMapper.map(accountUpdateDto, AccountEntity.class)).thenReturn(account);

		when(accountRepository.save(account)).thenReturn(savedAccount);
		when(modelMapper.map(savedAccount, AccountResponseDto.class)).thenReturn(expected);

		AccountResponseDto actual = authServiceImpl.createAccount(accountUpdateDto);

		verify(account).setRoleId(ERole.ROLE_USER);
		verify(account).setPassword(passwordEncoder.encode(account.getPassword()));
		verify(account).setIsActive(true);
		verify(account).setFullName(String.join(" ", account.getFirstName(), account.getLastName()));

		assertThat(expected, is(actual));

	}

}
