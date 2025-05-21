package com.doubleowner.revibe.domain.option.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OptionRequestDto {

    @NotNull(message = "사이즈 입력은 필수입니다.")
    @Schema(defaultValue = "250",description = "사이즈 입력")
    private int size;

}
