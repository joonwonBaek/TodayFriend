package com.todayfriend.member.dto.request;

import lombok.Getter;

@Getter
public class MemberLoginRequest {
    private String email;
    private String password;
}
