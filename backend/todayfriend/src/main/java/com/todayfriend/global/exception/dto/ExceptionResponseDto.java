package com.todayfriend.global.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionResponseDto {
    private int code;
    private String message;

    @Builder
    public ExceptionResponseDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
