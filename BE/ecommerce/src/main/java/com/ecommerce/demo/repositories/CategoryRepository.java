package com.ecommerce.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

}
