package com.example.SmartRestaurant.service.order;

import com.example.SmartRestaurant.dto.request.OrderItemDeleteRequest;
import com.example.SmartRestaurant.dto.request.StaffOrderRequest;
import com.example.SmartRestaurant.dto.response.OrderResponse;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderResponse create(StaffOrderRequest request, Long shopId);

    void deleteAll(Long shopId, OrderItemDeleteRequest request);

    OrderResponse createByQR(StaffOrderRequest request);

    List<OrderResponse> getAllByShopIdAndDate(Long shopId, LocalDate date);
}
