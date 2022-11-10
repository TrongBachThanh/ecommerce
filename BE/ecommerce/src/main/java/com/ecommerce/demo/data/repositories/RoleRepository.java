package com.ecommerce.demo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	
}
