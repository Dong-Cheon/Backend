package com.doubleowner.revibe.domain.coupon.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CouponRequestDto {

//    @NotBlank(message = "쿠폰이름 입력은 필수 값입니다") //유니크한 키 이름 설정
    @Schema(defaultValue = "쿠폰 이름",description = "쿠폰 이름")
    private String name;

//    @NotNull(message = "할인 가격 입력은 필수값입니다")
    @Schema(defaultValue = "10000",description = "할인 가격")
    private int price;

//    @NotNull(message = "쿠폰 전체 개수 입력은 필수값입니다")
    @Schema(defaultValue = "100",description = "총 발급 수량")
    private int totalQuantity;

    @Schema(defaultValue = "2023-01-18T11:22:33",description = "발급 가능 시작 시간")
    private LocalDateTime issuedStart;
    @Schema(defaultValue = "2023-01-19T11:22:33",description = "발급 가능 마감 시간")
    private LocalDateTime issuedEnd;

}
