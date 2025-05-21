package com.doubleowner.revibe.domain.item.dto.response;

import com.doubleowner.revibe.domain.item.entity.Category;
import com.doubleowner.revibe.domain.item.entity.Item;
import lombok.Getter;

@Getter
public class ItemResponseDto {

    private Long itemId;

    private String name;

    private String description;

    private Category category;

    private String imageUrl;

    private String brandName;

    private String adminName;

    private Long likeCount;

    public ItemResponseDto(Long itemId, String name, String description, Category category, String imageUrl, String brandName, String adminName, Long likeCount) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.brandName = brandName;
        this.adminName = adminName;
        this.likeCount = likeCount;
    }

    public static ItemResponseDto toDto(Item item) {
        return new ItemResponseDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getCategory(),
                item.getImageUrl(),
                item.getBrand().getName(),
                item.getUser().getNickname(),
                item.getLikeCount()
        );
    }

}