package com.example.SmartRestaurant.mapper;

import com.example.SmartRestaurant.dto.request.EmploymentRehireRequest;
import com.example.SmartRestaurant.dto.request.EmploymentRequest;
import com.example.SmartRestaurant.dto.response.EmploymentResponse;
import com.example.SmartRestaurant.entity.EmploymentEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmploymentMapper {
    UserMapper userMapper;
    ShopMapper shopMapper;

    public EmploymentEntity toEntity(EmploymentRequest request) {
        return EmploymentEntity.builder()
                .salary(request.getSalary())
                .workFrom(request.getWorkFrom())
                .build();
    }

    public EmploymentEntity toRehireEntity(EmploymentRehireRequest request) {
        return EmploymentEntity.builder()
                .salary(request.getSalary())
                .workFrom(request.getWorkFrom())
                .build();
    }

    public EmploymentResponse toResponse(EmploymentEntity employment) {
        return EmploymentResponse.builder()
                .id(employment.getId())
                .salary(employment.getSalary())
                .workFrom(employment.getWorkFrom())
                .endedAt(employment.getEndedAt())
                .status(employment.getStatus())
                .user(userMapper.toResponse(employment.getUser()))
                .shop(shopMapper.toResponse(employment.getShop()))
                .build();
    }
}
