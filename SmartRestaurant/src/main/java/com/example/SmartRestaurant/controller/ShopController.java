package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(Const.PREFIX_VERSION + "/shops")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopController {
    ShopService shopService;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping
    @Operation(summary = "Tạo shop mới")
    public ResponseEntity<ApiResponse<ShopResponse>> create(
            @Valid @RequestBody ShopRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                201, "Yêu cầu đã được chấp",
                shopService.create(request),
                LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/my")
    @Operation(summary = "Tìm tất cả các shop theo user")
    public ResponseEntity<ApiResponse<List<ShopResponse>>> getMyShops(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200, "Success",
                shopService.getAllByUserId(userDetails.getUser().getId()),
                LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/{id}")
    @Operation(summary = "Tìm shop theo id")
    public ResponseEntity<ApiResponse<ShopResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200, "Success", shopService.getById(id), LocalDateTime.now()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/{id}")
    @Operation(summary = "Chỉnh sửa thông tin shop theo id")
    public ResponseEntity<ApiResponse<ShopResponse>> update(
            @PathVariable Long id
            , @Valid @RequestBody ShopRequest request
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200, "Cập nhật thành công",
                shopService.updateCustom(id, request, userDetails),
                LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/approve")
    @Operation(summary = "Duyệt thông tin shop")
    public ResponseEntity<ApiResponse<ShopResponse>> approve(
            @PathVariable Long id) {
        shopService.activateShop(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200, "Cập nhật thành công",
                null,
                LocalDateTime.now()
        ));
    }
}
