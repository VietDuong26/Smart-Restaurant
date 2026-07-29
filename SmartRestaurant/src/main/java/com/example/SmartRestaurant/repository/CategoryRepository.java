package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.common.enums.CategoryStatus;
import com.example.SmartRestaurant.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Page<CategoryEntity> findAllByShopId(Long shopId, Pageable pageable);

    Page<CategoryEntity> findAllByShopIdAndStatus(Long shopId, CategoryStatus status, Pageable pageable);

    CategoryEntity findByNameAndShopId(String name, Long shopId);

    List<CategoryEntity> findByNameAndShopIdAndIdNot(String name, Long shopId, Long id);
    //tìm thằng cate trong cùng shop này có tên khác mà ngoại trừ ra thằng đang chọn
}
