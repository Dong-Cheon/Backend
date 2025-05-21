package com.doubleowner.revibe.domain.like.controller;

import com.doubleowner.revibe.domain.like.dto.LikeResponseDto;
import com.doubleowner.revibe.domain.like.service.LikeService;
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
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "좋아요 관련 API")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/likes")
    @Operation(summary = "좋아요 API",description = "상품을 좋아요 등록/해제 할수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "좋아요 등록/해제 성공하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<?>> doLike(
            @RequestParam(value = "itemId") Long itemId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            )
    {
        User loginUser = userDetails.getUser();
        if(likeService.doLike(loginUser,itemId)){
            return ResponseEntity.status(HttpStatus.OK).body(new CommonResponseBody<>("해당 상품을 관심상품으로 등록했습니다."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponseBody<>("해당상품을 관심상품에서 해제했습니다."));
    }

    // 좋아요(관심상품) 목록 조회
    @GetMapping("/likes")
    @Operation(summary = "좋아요한 상품 목록 API",description = "좋아요한 상품을 할수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "조회 성공하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<List<LikeResponseDto>>> getLikes(
            @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        User loginUser = userDetails.getUser();
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponseBody<>("관심상품 목록", likeService.findLikes(loginUser)));
    }
}
