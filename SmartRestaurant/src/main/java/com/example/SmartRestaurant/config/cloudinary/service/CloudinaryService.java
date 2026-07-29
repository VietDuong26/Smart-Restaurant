package com.example.SmartRestaurant.config.cloudinary.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String uploadQRImage(MultipartFile file, Long tableId);

    String uploadProductImage(MultipartFile file, Long productId);

}
