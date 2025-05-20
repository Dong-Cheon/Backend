package com.doubleowner.revibe.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserProfileUpdateRequestDto {

    @NotBlank(message = "비밀번호는 필수 값입니다")
    @Schema(defaultValue = "password",description = "현재 비밀번호")
    private final String password;

    @NotBlank(message = "닉네임은 필수 값입니다.")
    @Schema(defaultValue = "닉네임 변경",description = "변경할 닉네임")
    private final String nickname;

    @Schema(defaultValue = "010-1111-1111 ",description = "변경할 번호")
    private final String phoneNumber;
    @Schema(defaultValue = "경기도 ",description = "변경할 주소")
    private final String address;
}
