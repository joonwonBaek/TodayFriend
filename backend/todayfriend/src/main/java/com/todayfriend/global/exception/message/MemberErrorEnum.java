package com.todayfriend.global.exception.message;

import lombok.Getter;

@Getter
public enum MemberErrorEnum {
    MEMBER_ERROR_JOIN_DUPLICATE(1000,"중복된 아이디입니다");


    private int code;
    private String message;

    MemberErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
