package com.ecommerce.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.data.entities.RoleEntity;
import com.ecommerce.demo.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	private RoleService roleService;
	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}	
	@GetMapping
	List<RoleEntity> getRoles() {
		return roleService.getAllRoles();
	}
	
	
	

}
