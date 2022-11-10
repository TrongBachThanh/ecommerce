package com.ecommerce.demo.data.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.CategoryEntity;
import com.ecommerce.demo.data.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	Optional<ProductEntity> findByCode(String code);
	List<ProductEntity> findByIsNew(boolean isNew);

	List<ProductEntity> findByIsFeatured(boolean isFeatured);

	List<ProductEntity> findByCategory(CategoryEntity category);

	Optional<ProductEntity> findByName(String name);

	Page<ProductEntity> findAllByIsNewTrue(Pageable pageable);

	Page<ProductEntity> findAllByIsFeaturedTrue(Pageable pageable);

	Page<ProductEntity> findAllByIsDeletedFalseAndCategory_Id(Long categoryId, Pageable pageable);

	Page<ProductEntity> findAllByIsDeletedFalseAndNameLikeIgnoreCase(String query, Pageable pageable);

}
