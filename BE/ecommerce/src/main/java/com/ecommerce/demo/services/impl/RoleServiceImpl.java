package com.ecommerce.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.data.entities.RoleEntity;
import com.ecommerce.demo.repositories.RoleRepository;
import com.ecommerce.demo.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	private RoleRepository roleRepository;
	
	@Autowired 
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Override
	public List<RoleEntity> getAllRoles() {
		return roleRepository.findAll();
	}

}
