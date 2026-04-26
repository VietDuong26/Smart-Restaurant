package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.ResetPasswordTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordTokenEntity, Long> {
    ResetPasswordTokenEntity findByValueAndAndDeletedAtIsNull(String value);
}
