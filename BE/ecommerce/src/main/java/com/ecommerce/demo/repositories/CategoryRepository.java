package com.ecommerce.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	boolean existsByName(String name);

	Optional<CategoryEntity> findByCode(String code);

	public Optional<CategoryEntity> findByCodeAndIsDeletedFalse(String name);

	public List<CategoryEntity> findByIsDeletedFalse();

	public Optional<CategoryEntity> findByIdAndIsDeletedFalse(int id);

}
