package com.example.SmartRestaurant.service.product;

import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.ProductRequest;
import com.example.SmartRestaurant.dto.response.ProductResponse;
import com.example.SmartRestaurant.service.base.IBaseServiceAuthorization;

public interface ProductService extends IBaseServiceAuthorization<ProductRequest, ProductResponse, Long, CustomUserDetails> {
}
