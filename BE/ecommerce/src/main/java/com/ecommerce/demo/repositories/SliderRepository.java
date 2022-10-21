package com.ecommerce.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.SliderEntity;

public interface SliderRepository extends JpaRepository<SliderEntity, Long> {

}
