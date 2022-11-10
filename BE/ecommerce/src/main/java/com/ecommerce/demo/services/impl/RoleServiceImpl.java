package com.ecommerce.demo.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.RoleEntity;
import com.ecommerce.demo.data.repositories.RoleRepository;
import com.ecommerce.demo.dto.response.RoleResponseDto;
import com.ecommerce.demo.exceptions.ResourceFoundException;
import com.ecommerce.demo.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;
	private ModelMapper modelMapper;

	@Override
	public RoleResponseDto getRoleById(Long id) {
		Optional<RoleEntity> roleOptinal = this.roleRepository.findById(id);
		if (roleOptinal.isEmpty()) {
			throw new ResourceFoundException("Role Not Found");
		}
		RoleEntity role = roleOptinal.get();

		return modelMapper.map(role, RoleResponseDto.class);
	}

	@Override
	public RoleResponseDto createRole(RoleEntity role) {
		return null;
	}

	@Override
	public RoleResponseDto updateRole(RoleEntity role, Long id) {
		return null;
	}

	@Override
	public void deleteRole(Long id) {

	}

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
	}

}
