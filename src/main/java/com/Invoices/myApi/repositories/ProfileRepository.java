package com.Invoices.myApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Invoices.myApi.entities.ProfileEntity;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    ProfileEntity findByUid(long uid);

}
