package com.doubleowner.revibe.domain.sellbid.controller;

import com.doubleowner.revibe.domain.sellbid.dto.SellBidRequestDto;
import com.doubleowner.revibe.domain.sellbid.dto.SellBidResponseDto;
import com.doubleowner.revibe.domain.sellbid.service.SellBidService;
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
@RequestMapping("/api/sell-bids")
@RequiredArgsConstructor
@Tag(name = "판매 입찰 관련 API")
public class SellBidController {

    private final SellBidService sellBidService;
    /**
     *1 판매 입찰
     * @param requestDto - 판매입찰을 위한 요청 정보
     * @return responseDto - 판매입찰 완료 응답 dto
     */
    @PostMapping
    @Operation(summary = "판매 입찰 등록 API",description = "상품에 대한 판매 입찰 가격을 등록할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "판매 입찰 등록 성공 하면 200 OK") // todo ResponseCode 수정
    })
    public CommonResponseBody<?> createSellBid(
            @RequestBody @Valid SellBidRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User loginUser = userDetails.getUser();
        SellBidResponseDto responseDto = sellBidService.createSellBid(loginUser, requestDto);

        return new CommonResponseBody<>("판매 입찰이 완료되었습니다", responseDto);
    }

    /**
     *1 판매 입찰 조회
     * @RequestParam page - 페이지 생성을 위한 요청
     * @RequestParam size - 페이지 사이즈를 위한 요청
     * @return  판매입찰 조회 응답 message
     */
    @GetMapping
    @Operation(summary = "판매 입찰 조회 API",description = "상품에 대한 판매 입찰 가격을 조회할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "판매 입찰 조회 성공 하면 200 OK")
    })
    public CommonResponseBody<List<SellBidResponseDto>> findBuyBid(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size
    ) {
        User loginUser = userDetails.getUser();
        List<SellBidResponseDto> sellBidResponseDtos = sellBidService.findAllBuyBid(loginUser, page, size);
        return new CommonResponseBody<>("현재 사용자 판매 입찰 내역입니다.",sellBidResponseDtos );
    }

    @GetMapping("/{optionId}")
    @Operation(summary = "옵션 아이디로 입찰 조회 API",description = "상품에 대한 판매 입찰 가격을 옵션아이디로 조회할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "판매 입찰 조회 성공 하면 200 OK")
    })
    public CommonResponseBody<List<SellBidResponseDto>> findBuyBidByOptionId(
            @PathVariable Long optionId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "3") int size
    ){
        List<SellBidResponseDto> sellBidResponseDtos = sellBidService.findBuyBidByOptionId(optionId, page,size);
        return new CommonResponseBody<>("옵션에 대한 판매 입찰 내역입니다.", sellBidResponseDtos);
    }

    /**
     *1 판매 입찰 취소
     * @param sellBidId - 판매입찰 취소를 위한 요청 정보
     * @return  판매입찰 취소 응답 message
     */
    @DeleteMapping("/{sellBidId}")
    @Operation(summary = " 판매 입찰 취소 API",description = "상품에 대한 판매 입찰을 취소할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "판매 입찰 취소 성공 하면 200 OK")
    })
    public CommonResponseBody<?> deleteSellBid(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long sellBidId) {

        User loginUser = userDetails.getUser();
        sellBidService.deleteSellBid(loginUser, sellBidId);

        return new CommonResponseBody<>("판매 입찰이 취소되었습니다.", null);
    }
}
