package com.doubleowner.revibe.domain.buybid.dto;

import com.doubleowner.revibe.domain.buybid.entity.BuyBid;
import com.doubleowner.revibe.domain.option.entity.Option;
import com.doubleowner.revibe.domain.user.entity.User;
import com.doubleowner.revibe.global.common.enumType.BidStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BuyBidRequestDto {

    @NotNull
    @Min(20000)
    @Schema(defaultValue = "20000",description = "구매 입찰 가격 입력 20000원 이상")
    private Long price;

    @NotNull
    @Schema(defaultValue = "1",description = "옵션 id 입력") //todo 수정
    private Long optionId;

    public BuyBid toEntity(Option option , User user) {
        return BuyBid.builder()
                .price(this.price)
                .buyStatus(BidStatus.ONPROGRESS)
                .user(user)
                .option(option)
                .build();
    }

}