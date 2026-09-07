package com.example.SmartRestaurant.service.orderitem;

import com.example.SmartRestaurant.dto.request.StaffOrderItemRequest;
import com.example.SmartRestaurant.dto.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    List<OrderItemResponse> createAll(Long orderId,
                                      List<StaffOrderItemRequest> itemRequests);
}
