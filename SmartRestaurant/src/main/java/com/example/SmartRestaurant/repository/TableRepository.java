package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
    List<TableEntity> findByShopId(Long shopId);
}
