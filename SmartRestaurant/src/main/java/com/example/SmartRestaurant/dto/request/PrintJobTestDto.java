package com.example.SmartRestaurant.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrintJobTestDto {
    private Long id;
    private String type;
    private String contentData;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
