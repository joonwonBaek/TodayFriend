package com.todayfriend.member.dto;

import com.todayfriend.member.domain.Member;
import com.todayfriend.member.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberDto {

    private String email;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
