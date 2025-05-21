package com.doubleowner.revibe.domain.image.controller;

import com.doubleowner.revibe.domain.image.dto.response.ImageUploadResponseDto;
import com.doubleowner.revibe.domain.image.service.ImageService;
import com.doubleowner.revibe.global.common.dto.CommonResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/images")
@Tag(name = "이미지 등록 API")
public class ImageController {
    private final ImageService imageService;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "이미지 등록 API",description = "폼데이터로 이미지 등록을 하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "이미지 등록을 성공하면 200OK")
    })
    public ResponseEntity<CommonResponseBody<ImageUploadResponseDto>> uploadImage(@RequestPart("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(new CommonResponseBody<>("사진 등록이 완료 되었습니다.",imageService.getImageUrl(file)), HttpStatus.OK);

    }
}
