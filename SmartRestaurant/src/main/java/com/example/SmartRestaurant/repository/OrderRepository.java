package com.example.SmartRestaurant.repository;

import com.example.SmartRestaurant.common.enums.OrderStatus;
import com.example.SmartRestaurant.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByTableIdAndOrderStatus(Long tableId, OrderStatus pending);

}
