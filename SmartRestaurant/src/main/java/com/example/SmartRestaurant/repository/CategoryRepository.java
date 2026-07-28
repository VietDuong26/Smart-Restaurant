package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.common.enums.CategoryStatus;
import com.example.SmartRestaurant.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Page<CategoryEntity> findAllByShopId(Long shopId, Pageable pageable);

    Page<CategoryEntity> findAllByShopIdAndStatus(Long shopId, CategoryStatus status, Pageable pageable);
}
