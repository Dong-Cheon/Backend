package com.doubleowner.revibe.domain.item.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class ItemUpdateRequestDto {

    @Schema(defaultValue = "에어포스",description = "신발명")
    private String name;

    @Schema(defaultValue = "스니커즈",description = "카테고리 명")
    private String category;

    @Schema(defaultValue = "상품에 대한 설명",description = "상품 설명")
    private String description;

    @Schema(defaultValue = "상품에 대한 사진 주소",description = "사진 주소")
    private String image;
}
