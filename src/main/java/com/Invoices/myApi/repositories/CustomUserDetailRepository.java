package com.Invoices.myApi.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Invoices.myApi.entities.UserEntity;


@Repository
public interface CustomUserDetailRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
