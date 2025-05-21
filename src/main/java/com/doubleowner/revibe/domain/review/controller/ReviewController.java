package com.doubleowner.revibe.domain.review.controller;

import com.doubleowner.revibe.domain.review.dto.ReviewRequestDto;
import com.doubleowner.revibe.domain.review.dto.ReviewResponseDto;
import com.doubleowner.revibe.domain.review.dto.UpdateReviewRequestDto;
import com.doubleowner.revibe.domain.review.service.ReviewService;
import com.doubleowner.revibe.global.common.dto.CommonResponseBody;
import com.doubleowner.revibe.global.config.auth.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "리뷰 관련 API")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "리뷰 등록 API",description = "리뷰를 등록 할수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "리뷰 등록 성공하면 201 CREATED")
    })
    public ResponseEntity<CommonResponseBody<ReviewResponseDto>> writeReview(@RequestBody ReviewRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ReviewResponseDto review = reviewService.write(requestDto, userDetails.getUser());
        return new ResponseEntity<>(new CommonResponseBody<>("리뷰가 등록되었습니다.", review), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "리뷰 조회 API",description = "자신이 쓴 리뷰를 조회 할수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "리뷰 조회 성공하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<List<ReviewResponseDto>>> findReviews(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                                                   @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        List<ReviewResponseDto> read = reviewService.findReview(userDetails.getUser(), page, size);
        return new ResponseEntity<>(new CommonResponseBody<>("리뷰가 조회 되었습니다", read), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "리뷰 수정 API",description = "자신이 쓴 리뷰를 수정 할수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "리뷰 수정 성공하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<Void>> updateReview(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody UpdateReviewRequestDto updateReviewRequestDto) {
        reviewService.updateReview(id, userDetails, updateReviewRequestDto);
        return new ResponseEntity<>(new CommonResponseBody<>("리뷰가 수정되었습니다."), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "리뷰 삭제 API",description = "자신이 쓴 리뷰를 삭제 할수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "리뷰 삭제 성공하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<Void>> deleteReview(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.deleteReview(id, userDetails.getUser());
        return new ResponseEntity<>(new CommonResponseBody<>("리뷰가 삭제되었습니다."), HttpStatus.OK);
    }
}
