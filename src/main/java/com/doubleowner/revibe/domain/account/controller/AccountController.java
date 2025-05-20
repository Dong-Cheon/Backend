package com.doubleowner.revibe.domain.account.controller;

import com.doubleowner.revibe.domain.account.dto.AccountRequestDto;
import com.doubleowner.revibe.domain.account.dto.AccountResponseDto;
import com.doubleowner.revibe.domain.account.service.AccountService;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@Tag(name = "회원 계좌 관련 API")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "계좌 추가 API",description = "계좌를 추가 할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "계좌를 추가하면 201 CREATED")
    })
    public ResponseEntity<CommonResponseBody<AccountResponseDto>> createAccount(
            @Valid @RequestBody AccountRequestDto dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        AccountResponseDto accountResponseDto = accountService.create(userDetails.getUser(), dto);

        return new ResponseEntity<>(new CommonResponseBody<>("계좌 등록이 완료되었습니다.", accountResponseDto), HttpStatus.CREATED);

    }

    @GetMapping
    @Operation(summary = "계좌 조회 API",description = "계좌를 조회 할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "계좌를 조회하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<AccountResponseDto>> findAccount(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        AccountResponseDto accountResponseDto = accountService.findAccount(userDetails.getUser());

        return new ResponseEntity<>(new CommonResponseBody<>("나의 계좌 조회를 성공하였습니다.", accountResponseDto), HttpStatus.OK);
    }

    @PatchMapping
    @Operation(summary = "계좌 수정 API",description = "계좌를 수정 할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "계좌 수정 성공 하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<AccountResponseDto>> updateAccount(
            @RequestBody AccountRequestDto dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        AccountResponseDto accountResponseDto = accountService.updateAccount(userDetails.getUser(), dto);

        return new ResponseEntity<>(new CommonResponseBody<>("나의 계좌 정보를 수정하였습니다.", accountResponseDto), HttpStatus.OK);
    }

    @DeleteMapping
    @Operation(summary = "계좌 삭제 API",description = "계좌를 삭제 할수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "계좌를 삭제 성공 하면 200 OK")
    })
    public ResponseEntity<CommonResponseBody<Void>> delete(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        accountService.deleteAccount(userDetails.getUser());

        return new ResponseEntity<>(new CommonResponseBody<>("나의 계좌 삭제가 완료되었습니다."), HttpStatus.OK);
    }
}
