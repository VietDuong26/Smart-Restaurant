package com.example.SmartRestaurant.service.order;

import com.example.SmartRestaurant.dto.request.StaffOrderRequest;
import com.example.SmartRestaurant.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse create(StaffOrderRequest request, Long shopId);
}
