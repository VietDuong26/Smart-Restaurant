package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.service.shop.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(Const.PREFIX_VERSION + "/shops")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopController {
    ShopService shopService;

    @PostMapping
    @Operation(
            summary = "Tạo shop mới",
            description = "API dùng để tạo shop mới cho người dùng đang đăng nhập"
    )
    public ResponseEntity<ApiResponse<ShopResponse>> create(
            @Valid @RequestBody ShopRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                202, "Yêu cầu đã được chấp",
                shopService.create(request),
                LocalDateTime.now()
        ));
    }

    @GetMapping("/my")
    @Operation(
            summary = "Tìm tất cả các shop theo user",
            description = "API dùng để tìm tất cả các shop theo user id"
    )
    public ResponseEntity<ApiResponse<List<ShopResponse>>> getMyShops(@Valid @RequestParam Long userId) {
        return ResponseEntity.ok(new ApiResponse<>(
                200, "Success",
                shopService.getAllByUserId(userId),
                LocalDateTime.now()
        ));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Tìm shop theo id",
            description = "API dùng để tìm shop theo id"
    )
    public ResponseEntity<ApiResponse<ShopResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(
                200, "Success", shopService.getById(id), LocalDateTime.now()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ShopResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ShopRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(
                200, "Cập nhật thành công",
                shopService.update(id, request),
                LocalDateTime.now()
        ));
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<ShopResponse>> approve(
            @PathVariable Long id) {
        shopService.activateShop(id);
        return ResponseEntity.ok(new ApiResponse<>(
                200, "Cập nhật thành công",
                null,
                LocalDateTime.now()
        ));
    }
}
