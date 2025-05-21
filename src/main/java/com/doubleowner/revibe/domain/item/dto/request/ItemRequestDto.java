package com.doubleowner.revibe.domain.item.dto.request;

import com.doubleowner.revibe.domain.brand.entity.Brand;
import com.doubleowner.revibe.domain.item.entity.Category;
import com.doubleowner.revibe.domain.item.entity.Item;
import com.doubleowner.revibe.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class ItemRequestDto {
    @NotNull(message = "브랜드 입력은 필수입니다")
    @Schema(defaultValue = "브랜드",description = "브랜드 명")
    private String brandName;

    @NotBlank(message = "카테고리 입력은 필수입니다")
    @Schema(defaultValue = "스니커즈",description = "카테고리 명")
    private String category;

    @NotBlank(message = "상품이름 입력은 필수입니다")
    @Schema(defaultValue = "에어포스",description = "신발명")
    private String name;

    @NotBlank(message = "설명 입력은 필수입니다")
    @Schema(defaultValue = "상품에 대한 설명",description = "상품 설명")
    private String description;

    @NotNull(message = "상품 사진은 필수입니다")
    @Schema(defaultValue = "상품에 대한 사진 주소",description = "이미지 등록 API 에서 받은 url로 등록")
    private String imageUrl;

    public Item toEntity(Brand brand, String imageUrl, User loginUser) {
        return Item.builder()
                .brand(brand)
                .imageUrl(imageUrl)
                .user(loginUser)
                .category(Category.of(this.category))
                .name(this.name)
                .description(this.description)
                .likeCount(0L)
                .build();
    }
}
