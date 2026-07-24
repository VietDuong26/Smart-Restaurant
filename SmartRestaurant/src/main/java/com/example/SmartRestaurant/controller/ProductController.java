package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.ProductRequest;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.ProductResponse;
import com.example.SmartRestaurant.service.product.ProductService;
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

@RestController
@RequestMapping(Const.PREFIX_VERSION + "/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService service;


    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping
    @Operation(summary = "Tạo sản phẩm")
    public ResponseEntity<ApiResponse<ProductResponse>> create(
            @RequestBody ProductRequest request
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
    @Operation(summary = "Sửa sản phẩm")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(
            @PathVariable Long id
            , @RequestBody ProductRequest request
            , Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200
                , "Success"
                , service.update(id, request, userDetails)
                , LocalDateTime.now()
        ));
    }

//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
//    @Operation(summary = "Xóa sản phẩm")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse<?>> delete(
//            @PathVariable Long id
//            , Authentication authentication) {
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        service.delete(id, userDetails);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>(
//                204
//                , "Success"
//                , null
//                , LocalDateTime.now()
//        ));
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
//    @GetMapping("/{id}")
//    @Operation(summary = "Xem sản phẩm theo id")
//    public ResponseEntity<ApiResponse<ProductResponse>> getById(
//            @PathVariable Long id
//            , Authentication authentication) {
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
//                200
//                , "Success"
//                , service.getById(id, userDetails)
//                , LocalDateTime.now()
//        ));
//    }

//    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
//    @GetMapping("/category/{id}")
//    @Operation(summary = "Lấy tất cả sản phẩm theo danh mục")
//    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllByShopId(
//            @PathVariable("id") Long categoryId
//            , Authentication authentication) {
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
//                200
//                , "Success"
//                , service.getAllByCategoryId(categoryId, userDetails)
//                , LocalDateTime.now()
//        ));
//    }
}
