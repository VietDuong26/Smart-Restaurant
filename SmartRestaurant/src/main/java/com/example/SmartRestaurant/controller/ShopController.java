package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.service.shop.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.example.SmartRestaurant.common.Constant.URL;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(URL + "/shop")
public class ShopController {
    ShopService shopService;

    @PostMapping
    @Operation(summary = "Tạo shop mới")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<ApiResponse<ShopResponse>> createShop(
            @RequestBody ShopRequest request
    ) {
        return ResponseEntity.ok(new ApiResponse<ShopResponse>(
                201
                , "Thành công"
                , shopService.create(request)
                , LocalDateTime.now()
        ));
    }

//    /**
//     * OWNER
//     * Lấy shop của chính mình
//     */
//    @GetMapping("/me")
//    @PreAuthorize("hasAuthority('PERM_SHOP_READ')")
//    public ApiResponse<ShopResponse> getMyShop() {
//    }
//
//    /**
//     * OWNER hoặc ADMIN
//     * Xem thông tin shop theo id
//     */
//    @GetMapping("/{shopId}")
//    @PreAuthorize("hasAuthority('PERM_SHOP_READ')")
//    public ApiResponse<ShopResponse> getShop(
//            @PathVariable Long shopId
//    ) {
//    }
//
//    /**
//     * OWNER hoặc ADMIN
//     * Cập nhật thông tin shop
//     */
//    @PutMapping("/{shopId}")
//    @PreAuthorize("hasAuthority('PERM_SHOP_UPDATE')")
//    public ApiResponse<ShopResponse> updateShop(
//            @PathVariable Long shopId,
//            @Valid @RequestBody UpdateShopRequest request
//    ) {
//    }
//
//    /**
//     * OWNER hoặc ADMIN
//     * Đổi trạng thái shop
//     */
//    @PatchMapping("/{shopId}/status")
//    @PreAuthorize("hasAuthority('PERM_SHOP_UPDATE')")
//    public ApiResponse<ShopResponse> updateStatus(
//            @PathVariable Long shopId,
//            @Valid @RequestBody UpdateShopStatusRequest request
//    ) {
//    }
//
//    /**
//     * ADMIN
//     * Lấy danh sách tất cả shop
//     */
//    @GetMapping
//    @PreAuthorize("hasAuthority('PERM_SHOP_READ_ALL')")
//    public ApiResponse<PageResponse<ShopResponse>> getAllShops(
//            Pageable pageable
//    ) {
//    }
//
//    /**
//     * ADMIN
//     * Khóa shop
//     */
//    @PatchMapping("/{shopId}/suspend")
//    @PreAuthorize("hasAuthority('PERM_SHOP_SUSPEND')")
//    public ApiResponse<Void> suspendShop(
//            @PathVariable Long shopId
//    ) {
//    }
//
//    /**
//     * ADMIN
//     * Mở lại shop
//     */
//    @PatchMapping("/{shopId}/activate")
//    @PreAuthorize("hasAuthority('PERM_SHOP_ACTIVATE')")
//    public ApiResponse<Void> activateShop(
//            @PathVariable Long shopId
//    ) {
//    }

}
