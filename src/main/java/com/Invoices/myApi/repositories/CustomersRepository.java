package com.Invoices.myApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Invoices.myApi.entities.CustomerEntity;


@Repository
public interface CustomersRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByUid(long uid);


}
