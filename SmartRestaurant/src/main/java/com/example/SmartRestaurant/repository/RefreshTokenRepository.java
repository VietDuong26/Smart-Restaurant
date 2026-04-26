package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    RefreshTokenEntity findByValueAndDeletedAtIsNull(String refreshToken);

    List<RefreshTokenEntity> findAllByUser_IdAndDeletedAtIsNull(Long userId);
}
