package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.common.enums.EmploymentStatus;
import com.example.SmartRestaurant.entity.EmploymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentRepository extends JpaRepository<EmploymentEntity, Long> {
    boolean existsByUserIdAndShopIdAndStatus(Long currentUserId, Long shopId, EmploymentStatus status);
}
