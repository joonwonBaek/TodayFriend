package com.todayfriend.global.exception.message;

import lombok.Getter;

@Getter
public enum MemberErrorEnum {
    MEMBER_ERROR_JOIN_DUPLICATE(1000,"중복된 아이디입니다"),
    MEMBER_ERROR_EMAIL_NONE(1001,"회원 정보가 없습니다"),
    MEMBER_ERROR_PASSWORD_FAIL(1002,"비밀번호가 일치하지 않습니다");


    private int code;
    private String message;

    MemberErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
