package com.doubleowner.revibe.domain.user.dto.response;

import com.doubleowner.revibe.domain.user.entity.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserProfileResponseDto {

    private Long userId;

    private String nickname;

    private String email;

    private String address;

    private String phoneNumber;

    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public UserProfileResponseDto(Long userId, String nickname, String email, String address, String phoneNumber, UserStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
