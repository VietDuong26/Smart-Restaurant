package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.CategoryRequest;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.CategoryResponse;
import com.example.SmartRestaurant.service.category.CategoryService;
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
@RequestMapping(Const.PREFIX_VERSION + "/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService service;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping
    @Operation(summary = "Tạo danh mục")
    public ResponseEntity<ApiResponse<CategoryResponse>> create(
            @RequestBody CategoryRequest request
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                201
                , "Success"
                , service.create(request, userDetails)
                , LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Sửa danh mục")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(
            @PathVariable Long id
            , @RequestBody CategoryRequest request
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200
                , "Success"
                , service.update(id, request, userDetails)
                , LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @Operation(summary = "Xóa danh mục")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(
            @PathVariable Long id
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        service.delete(id, userDetails);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(
                204
                , "Success"
                , null
                , LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/{id}")
    @Operation(summary = "Xem danh mục theo id")
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(
            @PathVariable Long id
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200
                , "Success"
                , service.getById(id, userDetails)
                , LocalDateTime.now()
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/shop/{id}")
    @Operation(summary = "lấy tất cả danh mục theo shop")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllByShopId(
            @PathVariable("id") Long shopId
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200
                , "Success"
                , service.getAllByShopId(shopId, userDetails)
                , LocalDateTime.now()
        ));
    }
}
