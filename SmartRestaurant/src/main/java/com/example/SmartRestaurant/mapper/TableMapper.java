package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.TableRequest;
import com.example.SmartRestaurant.dto.response.TableResponse;
import com.example.SmartRestaurant.entity.TableEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TableMapper {

    public TableEntity toEntity(TableRequest request) {
        if (request == null) return null;

        return TableEntity.builder()
                .name(request.getName())
                .build();
    }

    public TableResponse toResponse(TableEntity table) {
        if (table == null) return null;

        return TableResponse.builder()
                .id(table.getId())
                .name(table.getName())
                .status(table.getStatus())
                .build();
    }
}
