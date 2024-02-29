package com.todayfriend.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDto {

    private Integer memberId;
    private String email;
}
