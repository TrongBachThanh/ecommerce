package com.ecommerce.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.dto.response.RoleResponseDto;
import com.ecommerce.demo.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	private RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("/{id}")
	public RoleResponseDto getRoleById(@PathVariable("id")Long id) {
		return this.roleService.getRoleById(id);
	}

}
