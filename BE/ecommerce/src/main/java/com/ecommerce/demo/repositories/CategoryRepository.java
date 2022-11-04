package com.ecommerce.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

	boolean existsByName(String name);
	Optional<CategoryEntity> findByCode(String code);

}
