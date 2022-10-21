package com.ecommerce.demo.dto.response;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ecommerce.demo.constants.ERole;

public class RoleResponseDto {
	private Long id;
	@Enumerated(EnumType.STRING)
	private ERole role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ERole getRole() {
		return role;
	}
	public void setRole(ERole role) {
		this.role = role;
	}
	
	
	
}
