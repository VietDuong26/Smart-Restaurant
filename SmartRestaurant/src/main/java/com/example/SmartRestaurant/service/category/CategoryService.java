package com.example.SmartRestaurant.service.category;

import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.CategoryRequest;
import com.example.SmartRestaurant.dto.response.CategoryResponse;
import com.example.SmartRestaurant.service.base.IBaseServiceAuthorization;

import java.util.List;

public interface CategoryService extends IBaseServiceAuthorization<CategoryRequest, CategoryResponse, Long, CustomUserDetails> {
    List<CategoryResponse> getAllByShopId(Long shopId, CustomUserDetails userDetails);
}
