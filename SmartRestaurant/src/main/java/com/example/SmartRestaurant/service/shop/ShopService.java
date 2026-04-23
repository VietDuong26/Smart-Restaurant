package com.example.SmartRestaurant.service.shop;

import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.service.base.IBaseService;

import java.util.List;

public interface ShopService extends IBaseService<ShopRequest, ShopResponse, Long> {
    List<ShopResponse> getAllByUserId(Long userId);

    void activateShop(Long id);
}
