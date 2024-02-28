package com.todayfriend.member.enums;

import lombok.Getter;

@Getter
public enum MemberResponseMessage {

    MEMBER_LOGIN_SUCCESS("회원 가입 성공");
    private final String message;

    MemberResponseMessage(String message) {
        this.message = message;
    }
}
