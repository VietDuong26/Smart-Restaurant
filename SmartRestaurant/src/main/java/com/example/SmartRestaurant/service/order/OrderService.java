package com.example.SmartRestaurant.service.order;

import com.example.SmartRestaurant.dto.request.OrderRequest;
import com.example.SmartRestaurant.dto.response.OrderResponse;
import com.example.SmartRestaurant.service.base.IBaseService;

public interface OrderService extends IBaseService<OrderRequest, OrderResponse, Long> {

}