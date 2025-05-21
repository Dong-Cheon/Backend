package com.doubleowner.revibe.domain.review.dto;

import com.doubleowner.revibe.domain.payment.entity.Payment;
import com.doubleowner.revibe.domain.review.entity.Review;
import com.doubleowner.revibe.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@Builder
public class ReviewRequestDto {

    @NotNull(message = "payment ID는 필수 값입니다")
    @Schema(defaultValue = "1",description = "결제 아이디 입력")
    private Long paymentId;

    @NotNull(message = "별점은 필수 값입니다.")
    @Min(value = 0, message = "별점은 0 이상이어야 합니다.")
    @Max(value = 5, message = "별점은 5 이하이어야 합니다.")
    @Schema(defaultValue = "1",description = "별점 입력 0~5점")
    private Integer starRate;

    @NotNull(message = "제목은 필수 값입니다.")
    @Schema(defaultValue = "리뷰 제목",description = "리뷰 제목")
    private String title;

    @NotNull(message = "내용은 필수 값입니다.")
    @Schema(defaultValue = "리뷰 내용",description = "리뷰 내용")
    private String content;
    
    @Schema(defaultValue = "리뷰 이미지",description = "이미지 주소")
    private String image;

    public static Review toEntity(ReviewRequestDto reviewRequestDto, Payment payment, User user, String image) {
        return Review.builder()
                .starRate(reviewRequestDto.getStarRate())
                .title(reviewRequestDto.getTitle())
                .content(reviewRequestDto.getContent())
                .reviewImage(image)
                .payment(payment)
                .item(payment.getExecution().getBuyBid().getOption().getItem())
                .user(user)
                .build();
    }
}
