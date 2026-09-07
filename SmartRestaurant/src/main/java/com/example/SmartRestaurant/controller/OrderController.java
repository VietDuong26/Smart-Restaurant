package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.dto.request.StaffOrderRequest;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.OrderResponse;
import com.example.SmartRestaurant.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.example.SmartRestaurant.common.Constant.URL;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(URL + "/order")
public class OrderController {
    OrderService orderService;

    @PostMapping("/{shopId}")
    @Operation(summary = "Tạo đơn hàng")
    @PreAuthorize("hasAuthority('PERM_ORDER_CREATE')")
    public ResponseEntity<ApiResponse<OrderResponse>> create(
            @PathVariable Long shopId,
            @RequestBody StaffOrderRequest request
    ) {

        return ResponseEntity.status(201).body(new ApiResponse<>(
                201
                , "Thành công"
                , orderService.create(request, shopId)
                , LocalDateTime.now()
        ));
    }


}
