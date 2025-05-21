package com.doubleowner.revibe.domain.cart.controller;

import com.doubleowner.revibe.domain.cart.dto.response.CartResponseDto;
import com.doubleowner.revibe.domain.cart.service.CartService;
import com.doubleowner.revibe.domain.user.entity.User;
import com.doubleowner.revibe.global.common.dto.CommonResponseBody;
import com.doubleowner.revibe.global.config.auth.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@Tag(name = "장바구니 관련 API")
public class CartController {

    private final CartService cartService;

    // 장바구니 담기
    @PostMapping
    @Operation(summary = "장바구니 등록 API",description = "장바구니를 등록 할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "장바구니 등록 성공하면 201 CREATED")
    })
    public ResponseEntity<CommonResponseBody<CartResponseDto>> addCart(
            @RequestParam Long optionId,
            @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        User loginUser = userDetails.getUser();
        CartResponseDto responseDto = cartService.addCart(loginUser, optionId);

        return ResponseEntity.status(HttpStatus.CREATED).body((new CommonResponseBody<>("상품을 장바구니에 등록했습니다.",responseDto)));
    }
    // 장바구니 조회
    @GetMapping
    @Operation(summary = "장바구니 조회 API",description = "장바구니를 조회 할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "장바구니 조회 성공하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<List<CartResponseDto>>> getCarts(
            @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        User loginUser = userDetails.getUser();
        List<CartResponseDto> responseDtos = cartService.getMyCarts(loginUser);

        return ResponseEntity.status(HttpStatus.OK).body((new CommonResponseBody<>("내 장바구니를 조회했습니다.",responseDtos)));
    }

    // 장바구니 삭제
    @DeleteMapping
    @Operation(summary = "장바구니 삭제 API",description = "장바구니를 삭제 할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "장바구니 삭제 성공하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody> deleteCart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam Long optionId)
    {
        User loginUser = userDetails.getUser();
        cartService.deleteCart(loginUser,optionId);

        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponseBody<>("장바구니에서 상품을 제거했습니다."));
    }
}


