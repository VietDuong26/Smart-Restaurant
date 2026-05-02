package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.common.TableStatus;
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
    @PostMapping
    @Operation(summary = "Tạo bàn mới")
    public ResponseEntity<ApiResponse<TableResponse>> createTable(
            @RequestBody TableRequest request
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                201
                , "Success"
                , tableService.create(request, userDetails)
                , LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    @Operation(summary = "Chỉnh sửa thông tin bàn")
    public ResponseEntity<ApiResponse<TableResponse>> updateTable(
            @PathVariable Long id
            , @RequestBody TableRequest request
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200
                , "Success"
                , tableService.update(id, request, userDetails)
                , LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa bàn")
    public ResponseEntity<ApiResponse<?>> deleteTable(
            @PathVariable Long id
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        tableService.delete(id, userDetails);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(
                204
                , "Success"
                , null
                , LocalDateTime.now()
        ));

    }

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
    @PatchMapping("/{id}/status")
    @Operation(summary = "Cập nhật trạng thái của bàn")
    public ResponseEntity<ApiResponse<?>> updateTableStatus(
            @PathVariable Long id
            , @RequestBody TableStatus status
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        tableService.updateStatus(id, status, userDetails);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(
                204
                , "Success"
                , null
                , LocalDateTime.now()
        ));
    }
}


