package com.Invoices.myApi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Invoices.myApi.entities.ProductEntity;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByUid(long uid);
}
