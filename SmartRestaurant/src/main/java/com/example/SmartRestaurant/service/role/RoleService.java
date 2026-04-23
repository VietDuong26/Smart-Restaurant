package com.example.SmartRestaurant.service.role;


import com.example.SmartRestaurant.dto.response.RoleResponse;
import com.example.SmartRestaurant.entity.RoleEntity;
import com.example.SmartRestaurant.service.base.IBaseService;

import java.util.List;

public interface RoleService extends IBaseService<RoleEntity, RoleResponse, Long> {


    List<RoleResponse> getAllByUserId(Long userId);
}
