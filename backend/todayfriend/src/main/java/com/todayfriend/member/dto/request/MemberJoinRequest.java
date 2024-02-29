package com.todayfriend.member.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class MemberJoinRequest {

    private String name;
    private String email;
    private String password;


}
