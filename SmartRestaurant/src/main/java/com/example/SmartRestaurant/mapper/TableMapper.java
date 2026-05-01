package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.TableRequest;
import com.example.SmartRestaurant.dto.response.TableResponse;
import com.example.SmartRestaurant.entity.TableEntity;
import org.springframework.stereotype.Component;

@Component
public class TableMapper {
    public TableEntity toEntity(TableRequest request) {
        if (request == null) return null;

        return TableEntity.builder()
                .name(request.getName())
                .qrCode(request.getQrCode())
                .build();
    }

    public TableResponse toResponse(TableEntity table) {
        if (table == null) return null;

        return TableResponse.builder()
                .id(table.getId())
                .name(table.getName())
                .status(table.getStatus())
                .qrCode(table.getQrCode())
                .build();
    }
}
