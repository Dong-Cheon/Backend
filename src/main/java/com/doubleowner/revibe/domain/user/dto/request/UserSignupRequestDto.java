package com.doubleowner.revibe.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserSignupRequestDto {

    @NotBlank(message = "닉네임은 필수 값입니다.")
    @Schema(defaultValue = "사용자",description = "닉네임")
    private final String nickname;

    @NotBlank(message = "이메일은 필수 값입니다.")
    @Schema(defaultValue = "test@test.com",description = "이메일")
    private final String email;

    @NotBlank(message = "비밀번호는 필수 값입니다")
    @Schema(defaultValue = "password",description = "패스워드")
    private final String password;

    @Schema(defaultValue = "경상북도",description = "주소")
    private final String address;
    @Schema(defaultValue = "010-0000-0000",description = "휴대폰 번호")
    private final String phoneNumber;

}
