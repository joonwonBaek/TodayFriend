package com.todayfriend.member.service;

import com.todayfriend.member.dto.LoginDto;
import com.todayfriend.member.dto.TokenDto;
import com.todayfriend.member.dto.request.MemberJoinRequest;
import com.todayfriend.member.dto.request.MemberLoginRequest;
import com.todayfriend.member.dto.response.MemberJoinResponse;

public interface MemberService {

    MemberJoinResponse signUp(MemberJoinRequest memberJoinRequest);

    TokenDto login(MemberLoginRequest memberLoginRequest);

    TokenDto reissue(String refreshToken);
}
