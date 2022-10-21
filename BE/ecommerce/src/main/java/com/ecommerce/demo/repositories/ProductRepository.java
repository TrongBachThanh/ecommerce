package com.ecommerce.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}
