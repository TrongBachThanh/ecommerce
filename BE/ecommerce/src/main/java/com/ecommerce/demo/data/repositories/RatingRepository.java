package com.ecommerce.demo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.RatingEntity;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

}
