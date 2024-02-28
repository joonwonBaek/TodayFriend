package com.todayfriend.member.service;

import com.todayfriend.member.dto.request.MemberJoinRequest;
import com.todayfriend.member.dto.response.MemberJoinResponse;

public interface MemberService {

    MemberJoinResponse signUp(MemberJoinRequest memberJoinRequest);
}
