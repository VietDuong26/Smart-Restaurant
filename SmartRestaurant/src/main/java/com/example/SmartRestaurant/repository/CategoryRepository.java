package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByShopIdAndName(Long id, String name);

    List<CategoryEntity> findAllByShopId(Long shopId);
}
