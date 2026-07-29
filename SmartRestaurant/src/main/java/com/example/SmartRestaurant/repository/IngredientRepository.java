package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.common.enums.IngredientStatus;
import com.example.SmartRestaurant.entity.IngredientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    boolean existsByNameAndShopId(String name, Long parentId);

    boolean existsByNameAndShopIdAndIdNot(String name, Long shopId, Long id);

    Page<IngredientEntity> findAllByShopId(Long shopId, Pageable pageable);

    Page<IngredientEntity> findAllByShopIdAndStatus(Long shopId, IngredientStatus status, Pageable pageable);

}
