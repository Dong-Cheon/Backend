package com.doubleowner.revibe.domain.image.service;

import com.doubleowner.revibe.domain.image.dto.response.ImageUploadResponseDto;
import com.doubleowner.revibe.global.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final S3Uploader s3Uploader;

    public ImageUploadResponseDto getImageUrl(MultipartFile file) throws IOException {

            String imageUrl = s3Uploader.upload(file);
            return new ImageUploadResponseDto(imageUrl);
        }
}
