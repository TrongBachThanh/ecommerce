package com.ecommerce.demo.services;

import com.ecommerce.demo.data.entities.RoleEntity;
import com.ecommerce.demo.dto.response.RoleResponseDto;

public interface RoleService {
	
	public RoleResponseDto getRoleById(Long id);
	
	public RoleResponseDto createRole(RoleEntity role);
	
	public RoleResponseDto updateRole(RoleEntity role, Long id);
	
	public void deleteRole(Long id);
}
