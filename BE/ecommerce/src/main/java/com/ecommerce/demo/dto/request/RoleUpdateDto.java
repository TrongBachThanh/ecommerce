package com.ecommerce.demo.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ecommerce.demo.constants.ERole;

public class RoleUpdateDto {
	@Enumerated(EnumType.STRING)
	private ERole role;
}
