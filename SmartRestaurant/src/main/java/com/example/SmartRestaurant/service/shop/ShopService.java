package com.example.SmartRestaurant.service.shop;

import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.service.base.IBaseServiceAuthorization;

import java.util.List;

public interface ShopService extends IBaseServiceAuthorization<ShopRequest, ShopResponse, Long, CustomUserDetails> {
    List<ShopResponse> getAllByUserId(Long userId);

    ShopResponse activateShop(Long id);

}
