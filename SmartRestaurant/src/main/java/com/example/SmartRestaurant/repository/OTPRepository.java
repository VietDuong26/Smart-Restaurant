package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.common.OTPStatus;
import com.example.SmartRestaurant.entity.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTPEntity, Long> {

    OTPEntity findByUser_EmailAndStatus(String email, OTPStatus status);
}
