package com.todayfriend.global.exception.message;

import lombok.Getter;

@Getter
public enum TokenErrorEnum {

    TOKEN_ERROR_REFRESH_NONEQUAL(2000,"리프레쉬 토큰이 일치하지 않습니다");


    private int code;
    private String message;

    TokenErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

