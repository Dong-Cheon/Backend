package com.doubleowner.revibe.domain.coupon.controller;

import com.doubleowner.revibe.domain.coupon.dto.response.IssuedCouponResponseDto;
import com.doubleowner.revibe.domain.coupon.service.IssuedCouponService;
import com.doubleowner.revibe.global.common.dto.CommonResponseBody;
import com.doubleowner.revibe.global.config.auth.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issued-coupons")
@RequiredArgsConstructor
public class IssuedCouponController {

    private final IssuedCouponService issuedCouponService;

    /**
     * 쿠폰 발급 요청하는 컨트롤러
     * @return
     */
    @PostMapping("/{id}")
    @Operation(summary = "쿠폰 발급 API",description = "사용자는 쿠폰을 발급할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "쿠폰을 발급 성공하면 201 CREATED")
    })
    public ResponseEntity<CommonResponseBody<IssuedCouponResponseDto>> issueCoupon(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){

        IssuedCouponResponseDto issuedCouponResponseDto = issuedCouponService.issuedCoupon(id, userDetails.getUser());

        return new ResponseEntity<>(new CommonResponseBody<>("쿠폰 등록이 완료되었습니다.", issuedCouponResponseDto), HttpStatus.CREATED);
    }

    /**
     * 발급된 쿠폰 조회
     * @param userDetails
     * @return
     */
    @GetMapping
    @Operation(summary = "발급된 쿠폰 조회 API",description = "발급 쿠폰 조회.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "발급된 쿠폰 조회 성공하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<List<IssuedCouponResponseDto>>> getIssuedCoupons(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size
    ) {
        List<IssuedCouponResponseDto> coupons = issuedCouponService.getUserCoupons(userDetails.getUser(), page, size);

        return new ResponseEntity<>(new CommonResponseBody<>("발급된 쿠폰을 조회하였습니다.", coupons), HttpStatus.OK);
    }

    /**
     * 쿠폰 사용 현황
     * @param id
     * @param userDetails
     * @return
     */
    @PatchMapping("/{id}/used")
    @Operation(summary = "발급된 쿠폰 사용 API",description = "발급 쿠폰 사용.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "발급된 쿠폰 사용 성공하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<IssuedCouponResponseDto>> usedCoupon(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        issuedCouponService.usedCoupon(id, userDetails.getUser());
        return new ResponseEntity<>(new CommonResponseBody<>("쿠폰 사용이 완료되었습니다."), HttpStatus.OK);
    }

}
