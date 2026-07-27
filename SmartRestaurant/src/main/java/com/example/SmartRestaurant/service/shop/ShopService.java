package com.example.SmartRestaurant.service.shop;

import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.service.base.BaseService;

import java.util.List;

public interface ShopService extends BaseService<ShopRequest, ShopResponse, Long> {

    List<ShopResponse> getAllShopOfCurrentUser();

    ShopResponse getShopByIdOfCurrentUser(Long id);
}
