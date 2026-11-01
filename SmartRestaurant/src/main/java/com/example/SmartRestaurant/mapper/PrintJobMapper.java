package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.PrintJobRequest;
import com.example.SmartRestaurant.entity.PrintJobEntity;
import org.springframework.stereotype.Component;

@Component
public class PrintJobMapper {
    public PrintJobEntity toEntity(PrintJobRequest request) {
        return PrintJobEntity.builder()
                .type(request.getType())
                .contentData(request.getContentData())
                .hostDevice(request.getHostDevice())
                .build();
    }
}
