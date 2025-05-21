package com.doubleowner.revibe.domain.brand.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BrandRequestDto {
    @Schema(defaultValue = "브랜드",description = "브랜드 명 입력")
    @NotBlank(message = "브랜드명 입력은 필수입니다.")
    private String name;
}
