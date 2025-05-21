package com.doubleowner.revibe.domain.brand.controller;

import com.doubleowner.revibe.domain.brand.dto.BrandRequestDto;
import com.doubleowner.revibe.domain.brand.dto.BrandResponseDto;
import com.doubleowner.revibe.domain.brand.service.BrandService;
import com.doubleowner.revibe.global.common.dto.CommonResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@Tag(name = "브랜드 관련 API")
public class BrandController {

    private final BrandService brandService;

    // 브랜드 등록
    @PostMapping
    @Operation(summary = "브랜드 등록 API",description = "관리자는 브랜드를 등록할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "브랜드 등록 성공 하면 201 CREATED")
    })
    public ResponseEntity<CommonResponseBody<BrandResponseDto>> createBrand(
            @Valid @RequestBody BrandRequestDto requestDto)
    {
        BrandResponseDto responseDto = brandService.createBrand(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponseBody<>("브랜드를 등록했습니다",responseDto));
    }

}
