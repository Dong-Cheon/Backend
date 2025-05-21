package com.doubleowner.revibe.domain.buybid.controller;

import com.doubleowner.revibe.domain.buybid.dto.BuyBidRequestDto;
import com.doubleowner.revibe.domain.buybid.dto.BuyBidResponseDto;
import com.doubleowner.revibe.domain.buybid.service.BuyBidService;
import com.doubleowner.revibe.domain.user.entity.User;
import com.doubleowner.revibe.global.common.dto.CommonResponseBody;
import com.doubleowner.revibe.global.config.auth.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buy-bids")
@RequiredArgsConstructor
@Tag(name = "구매 입찰 관련 API")
public class BuyBidController {

    private final BuyBidService bidService;
    /**
     *1 구매 입찰
     * @param requestDto - 구매입찰을 위한 요청 정보
     * @return responseDto - 구매입찰 완료 응답 dto
     */
    @PostMapping
    @Operation(summary = "구매 입찰 등록 API",description = "상품에 대한 구매 입찰 가격을 등록할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "구매 입찰 등록 성공 하면 200 OK") // todo ResponseCode 수정
    })
    public CommonResponseBody<?> createBuyBid(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid  BuyBidRequestDto requestDto) {

        User loginUser = userDetails.getUser();
        BuyBidResponseDto responseDto = bidService.createBuyBid(loginUser ,requestDto);

        return new CommonResponseBody<>("구매 등록이 완료되었습니다." , responseDto);
    }

    /**
     *1 구매 입찰 조회
     * @RequestParam page - 페이지 생성을 위한 요청
     * @RequestParam size - 페이지 사이즈를 위한 요청
     * @return  구매입찰 조회 응답 message
     */
    @GetMapping
    @Operation(summary = "구매 입찰 조회 API",description = "상품에 대한 구매 입찰 가격을 조회할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "구매 입찰 조회 성공 하면 200 OK")
    })
    public CommonResponseBody<List<BuyBidResponseDto>> findBuyBid(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size
    ) {
        User loginUser = userDetails.getUser();
        List<BuyBidResponseDto> buyBidResponseDtos = bidService.findAllBuyBid(loginUser, page, size);
        return new CommonResponseBody<>("사용자 구매 입찰 내역입니다.",buyBidResponseDtos );
    }

    @GetMapping("/{optionId}")
    @Operation(summary = "옵션 아이디로 입찰 조회 API",description = "상품에 대한 구매 입찰 가격을 옵션아이디로 조회할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "구매 입찰 조회 성공 하면 200 OK")
    })
    public CommonResponseBody<List<BuyBidResponseDto>> findBuyBidByOptionId(
            @PathVariable Long optionId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size
    ) {
        List<BuyBidResponseDto> buyBidResponseDtos = bidService.findBuyBidByOptionId(optionId, page, size);
        return new CommonResponseBody<>("옵션에 대한 구매 입찰 내역입니다.",buyBidResponseDtos );
    }


    /**
     *1 구매 입찰 취소
     * @param buyBidId - 구매입찰 취소를 위한 요청 정보
     * @return  구매입찰 취소 응답 message
     */
    @DeleteMapping("/{buyBidId}/refund")
    @Operation(summary = " 구매 입찰 취소 API",description = "상품에 대한 구매 입찰을 취소할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "구매 입찰 취소 성공 하면 200 OK")
    })
    public CommonResponseBody<?> deleteBuyBid(
            @PathVariable Long buyBidId){

        bidService.deleteBuyBid(buyBidId);

        return new CommonResponseBody<>("구매 입찰이 취소되었습니다.", null);
    }


}
