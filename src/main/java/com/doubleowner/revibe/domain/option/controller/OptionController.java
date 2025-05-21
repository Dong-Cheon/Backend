package com.doubleowner.revibe.domain.option.controller;

import com.doubleowner.revibe.domain.option.dto.request.OptionRequestDto;
import com.doubleowner.revibe.domain.option.dto.response.OptionResponseDto;
import com.doubleowner.revibe.domain.option.service.OptionService;
import com.doubleowner.revibe.global.common.dto.CommonResponseBody;
import com.doubleowner.revibe.global.config.auth.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@Tag(name = "옵션 관련 API")
public class OptionController {

    private final OptionService optionService;

    // 옵션 등록
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{itemId}/options")
    @Operation(summary = "상품 옵션 등록 API",description = "상품의 옵션을 등록 할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "옵션 등록 성공하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<OptionResponseDto>> createOption(
            @PathVariable Long itemId, @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody OptionRequestDto requestDto)
    {
        OptionResponseDto optionResponseDto = optionService.createOption(itemId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponseBody<>("상품에 옵션을 등록했습니다.",optionResponseDto));

    }

}
