package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
    List<ShopEntity> findAllByUser_Id(Long id);

    ShopEntity findShopEntityById(Long id);
}
