package com.ecommerce.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.data.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	List<ProductEntity> findByIsNew(boolean isNew);
	
	List<ProductEntity> findByIsFeatured(boolean isFeatured);
	
	List<ProductEntity> findByCategory(CategoryEntity category);
	
	Optional<ProductEntity> findByName(String name);

}
