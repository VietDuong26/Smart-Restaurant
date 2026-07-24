package com.example.SmartRestaurant.util.cloudinary;

import com.cloudinary.Cloudinary;
import com.example.SmartRestaurant.dto.response.ImageResponse;
import com.example.SmartRestaurant.exception.ImageFileTooLargeException;
import com.example.SmartRestaurant.exception.ImageUploadException;
import com.example.SmartRestaurant.exception.InvalidImageFileTypeException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageService {
    Cloudinary cloudinary;

    public ImageResponse upload(MultipartFile file) {
        try {
            if (!file.getContentType().startsWith("image/")) {
                throw new InvalidImageFileTypeException();
            }

            if (file.getSize() > 2 * 1024 * 1024) {
                throw new ImageFileTooLargeException();
            }
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of(
                    "folder", "SmartRestaurant/product"
            ));
            return ImageResponse.builder()
                    .url(uploadResult.get("secure_url").toString())
                    .publicId(uploadResult.get("public_id").toString())
                    .build();
        } catch (IOException e) {
            throw new ImageUploadException(e.getCause().toString());
        }
    }
}
