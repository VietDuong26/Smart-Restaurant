package com.example.SmartRestaurant.service.table;

import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.TableRequest;
import com.example.SmartRestaurant.dto.response.TableResponse;
import com.example.SmartRestaurant.service.base.IBaseService;

import java.util.List;

public interface TableService extends IBaseService<TableRequest, TableResponse, Long> {
    List<TableResponse> getTablesByShopId(Long shopId, CustomUserDetails userDetails);
}
