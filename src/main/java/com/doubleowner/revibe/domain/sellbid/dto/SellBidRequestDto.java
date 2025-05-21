package com.doubleowner.revibe.domain.sellbid.dto;

import com.doubleowner.revibe.domain.option.entity.Option;
import com.doubleowner.revibe.domain.sellbid.entity.SellBid;
import com.doubleowner.revibe.domain.user.entity.User;
import com.doubleowner.revibe.global.common.enumType.BidStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SellBidRequestDto {

    @NotNull
    @Schema(defaultValue = "1",description = "옵션 id 입력") //todo 수정
    private Long optionId;

    @NotNull
    @Schema(defaultValue = "20000",description = "판매 입찰 가격")
    private Long price;


    public SellBid toEntity(Option option, User user) {
        return SellBid.builder()
                .price(this.price)
                .status(BidStatus.ONPROGRESS)
                .user(user)
                .options(option)
                .build();
    }
}
