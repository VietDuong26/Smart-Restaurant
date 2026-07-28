package com.example.SmartRestaurant.service.category;

import com.example.SmartRestaurant.common.enums.CategoryStatus;
import com.example.SmartRestaurant.dto.request.CategoryRequest;
import com.example.SmartRestaurant.dto.response.CategoryResponse;
import com.example.SmartRestaurant.service.base.ShopResourceBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService extends ShopResourceBaseService<CategoryRequest, CategoryResponse, Long> {
    Page<CategoryResponse> getAllByShopId(Long shopId, CategoryStatus status, Pageable pageable);
}
