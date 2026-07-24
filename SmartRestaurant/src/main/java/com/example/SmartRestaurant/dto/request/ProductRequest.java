package com.example.SmartRestaurant.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = true, message = "Giá phải >= 0")
    private Float price;
    private String description;

    @NotBlank(message = "URL ảnh không được để trống")
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "URL ảnh phải hợp lệ"
    )
    private String imageUrl;

    @NotBlank(message = "Image public id không được để trống")
    private String imagePublicId;

    @NotNull(message = "CategoryId không được để trống")
    private Long categoryId;

}
