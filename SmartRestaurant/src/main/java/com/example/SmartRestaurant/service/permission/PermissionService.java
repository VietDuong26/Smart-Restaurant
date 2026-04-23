package com.example.SmartRestaurant.service.permission;


import com.example.SmartRestaurant.dto.response.PermissionResponse;
import com.example.SmartRestaurant.entity.PermissionEntity;
import com.example.SmartRestaurant.service.base.IBaseService;

import java.util.List;

public interface PermissionService extends IBaseService<PermissionEntity, PermissionResponse, Long> {


    List<PermissionResponse> getAllByUserId(Long userId);
}
