package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.entity.ShopEntity;
import org.springframework.stereotype.Component;

@Component
public class ShopMapper {
    public static ShopEntity toEntity(ShopRequest request) {
        if (request == null) return null;

        return ShopEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .openTime(request.getOpenTime())
                .closeTime(request.getCloseTime())
                .build();
    }

    public static ShopResponse toResponse(ShopEntity shop) {
        if (shop == null) return null;

        return ShopResponse.builder()
                .id(shop.getId())
                .name(shop.getName())
                .address(shop.getAddress())
                .phoneNumber(shop.getPhoneNumber())
                .userId(shop.getUser().getId())
                .ownerName(shop.getUser().getName())
                .openTime(shop.getOpenTime())
                .closeTime(shop.getCloseTime())
                .build();
    }
}
