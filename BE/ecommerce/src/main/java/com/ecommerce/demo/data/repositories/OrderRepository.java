package com.ecommerce.demo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.demo.data.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
