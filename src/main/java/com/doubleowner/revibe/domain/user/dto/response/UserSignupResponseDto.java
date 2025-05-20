package com.doubleowner.revibe.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserSignupResponseDto {

    private Long userId;

    private String nickname;

    private String email;

    private String profileImage;

    private String address;

    private String phoneNumber;

    private LocalDateTime createdAt;

}
