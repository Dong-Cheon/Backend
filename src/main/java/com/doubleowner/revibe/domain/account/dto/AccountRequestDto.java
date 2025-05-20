package com.doubleowner.revibe.domain.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AccountRequestDto {

    @NotBlank(message = "은행 이름은 필수 값입니다")
    @Schema(defaultValue = "농협",description = "은행명")
    private String bank;

    @NotNull(message = "계좌 번호는 필수 값입니다")
    @NotBlank(message = "계좌 번호는 공백일 수 없습니다.")
    @Schema(defaultValue = "000000000000",description = "계좌번호")
    private String account;

}
