package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.PrintJobRequest;
import com.example.SmartRestaurant.dto.response.PrintJobResponse;
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

    public PrintJobResponse toResponse(PrintJobEntity printJob) {
        return PrintJobResponse.builder()
                .id(printJob.getId())
                .type(printJob.getType())
                .contentData(printJob.getContentData())
                .build();
    }
}
