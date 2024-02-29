package com.todayfriend.member.enums;

import lombok.Getter;

@Getter
public enum MemberResponseMessage {

    MEMBER_JOIN_SUCCESS("회원 가입 성공"),
    MEMBER_LOGIN_SUCCESS("로그인 성공"),
    MEMBER_REISSUE_SUCCESS("토큰 재발급 성공");
    private final String message;

    MemberResponseMessage(String message) {
        this.message = message;
    }

}
