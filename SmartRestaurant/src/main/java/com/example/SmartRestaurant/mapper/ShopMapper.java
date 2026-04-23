package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.entity.ShopEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ShopMapper {
    public ShopEntity toEntity(ShopRequest request) {
        if (request == null) return null;

        return ShopEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .openTime(request.getOpenTime())
                .closeTime(request.getCloseTime())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public ShopResponse toResponse(ShopEntity shop) {
        if (shop == null) return null;

        return ShopResponse.builder()
                .id(shop.getId())
                .name(shop.getName())
                .address(shop.getAddress())
                .phoneNumber(shop.getPhoneNumber())
                .openTime(shop.getOpenTime())
                .closeTime(shop.getCloseTime())
                .createdAt(shop.getCreatedAt())
                .build();
    }
}
