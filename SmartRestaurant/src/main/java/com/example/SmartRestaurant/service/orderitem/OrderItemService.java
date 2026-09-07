package com.example.SmartRestaurant.service.orderitem;

import com.example.SmartRestaurant.dto.request.StaffOrderItemRequest;
import com.example.SmartRestaurant.dto.response.OrderItemResponse;
import com.example.SmartRestaurant.entity.OrderEntity;

import java.util.List;

public interface OrderItemService {
    List<OrderItemResponse> createAll(OrderEntity order,
                                      List<StaffOrderItemRequest> itemRequests);


    void deleteAll(OrderEntity order, List<Long> orderItemIds);

}
