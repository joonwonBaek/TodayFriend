package com.todayfriend.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberJoinResponse {
    private String email;
}
