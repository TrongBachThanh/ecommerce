package com.ecommerce.demo.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.demo.data.entities.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	Optional<AccountEntity> findById(Long id);
	Optional<AccountEntity> findByUsername(String username);
}
