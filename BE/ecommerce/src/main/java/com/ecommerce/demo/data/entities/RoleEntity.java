package com.ecommerce.demo.data.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.ecommerce.demo.constants.ERole;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{
	@Enumerated(EnumType.STRING)
	private ERole role;

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}
	
	
}
