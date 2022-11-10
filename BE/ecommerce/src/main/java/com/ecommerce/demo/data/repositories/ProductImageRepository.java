package com.ecommerce.demo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.ProductImageEntity;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long>{
	
}
