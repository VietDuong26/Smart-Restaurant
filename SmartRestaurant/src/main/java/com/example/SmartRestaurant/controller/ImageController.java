package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.ImageResponse;
import com.example.SmartRestaurant.util.cloudinary.ImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping(Const.PREFIX_VERSION + "/image")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageController {
    ImageService imageService;

    ResponseEntity<ApiResponse<ImageResponse>> upload(@ModelAttribute MultipartFile file) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200
                , "Success"
                , imageService.upload(file)
                , LocalDateTime.now()
        ));
    }
}
