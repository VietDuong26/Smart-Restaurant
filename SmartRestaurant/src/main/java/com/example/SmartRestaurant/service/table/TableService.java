package com.example.SmartRestaurant.service.table;

import com.example.SmartRestaurant.common.TableStatus;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.TableRequest;
import com.example.SmartRestaurant.dto.response.TableResponse;
import com.example.SmartRestaurant.service.base.IBaseServiceAuthorization;

import java.util.List;

public interface TableService extends IBaseServiceAuthorization<TableRequest, TableResponse, Long, CustomUserDetails> {

    List<TableResponse> getTablesByShopId(Long shopId, CustomUserDetails userDetails);

    void updateStatus(Long tableId, TableStatus status, CustomUserDetails userDetails);
}
