package com.todayfriend.member.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Data
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
