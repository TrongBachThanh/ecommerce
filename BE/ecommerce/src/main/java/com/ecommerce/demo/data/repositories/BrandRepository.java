package com.ecommerce.demo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.BrandEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, Long>{

}
