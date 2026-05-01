package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.TableRequest;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.TableResponse;
import com.example.SmartRestaurant.service.table.TableService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(Const.PREFIX_VERSION + "/tables")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TableController {
    TableService tableService;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/shop/{id}/tables")
    @Operation(summary = "Lấy danh sách bàn theo shop")
    public ResponseEntity<ApiResponse<List<TableResponse>>> getTablesByShop(
            @PathVariable("id") Long shopId
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200
                , "Success"
                , tableService.getTablesByShopId(shopId, userDetails)
                , LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping
    @Operation(summary = "Tạo bàn mới")
    public ResponseEntity<ApiResponse<TableResponse>> createTable(@RequestBody TableRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                201
                , "Success"
                , tableService.create(request)
                , LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    @Operation(summary = "Chỉnh sửa thông tin bàn")
    public ResponseEntity<ApiResponse<TableResponse>> updateTable(
            @PathVariable Long id,
            @RequestBody TableRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200
                , "Success"
                , tableService.update(id, request)
                , LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa bàn")
    public ResponseEntity<ApiResponse<?>> deleteTable(@PathVariable Long id) {
        tableService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(
                204
                , "Success"
                , null
                , LocalDateTime.now()
        ));

    }

//    @PatchMapping("/{id}/status")
//    @Operation(summary = "Cập nhật trạng thái của bàn")
//    public ResponseEntity<TableResponse> updateTableStatus(
//            @PathVariable Long id,
//            @RequestBody StatusRequest statusRequest) {
//        TableResponse updatedTable = tableService.updateStatus(id, statusRequest.getStatus());
//        return ResponseEntity.ok(updatedTable);
//    }
//
//    @PostMapping("/{id}/reserve")
//    @Operation(summary = "Đặt bàn")
//    public ResponseEntity<TableResponse> reserveTable(
//            @PathVariable Long id,
//            @RequestBody ReservationRequest request) {
//        TableResponse reservedTable = tableService.reserveTable(id, request);
//        return ResponseEntity.ok(reservedTable);
//    }
//
//    @PostMapping("/{id}/release")
//    @Operation(summary = "Đóng bàn")
//    public ResponseEntity<TableResponse> releaseTable(@PathVariable Long id) {
//        TableResponse releasedTable = tableService.releaseTable(id);
//        return ResponseEntity.ok(releasedTable);
//    }
}


