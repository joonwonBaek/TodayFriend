package com.todayfriend.member.controller;

import com.todayfriend.global.util.ResponseTemplate;
import com.todayfriend.member.dto.request.MemberJoinRequest;
import com.todayfriend.member.dto.response.MemberJoinResponse;
import com.todayfriend.member.enums.MemberResponseMessage;
import com.todayfriend.member.service.MemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Mebers", description = "멤버 관련 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;


    @Operation(summary = "회원 가입", description = "회원가입(아이디, 비밀번호) 입력")
    @PostMapping("/join")
    public ResponseEntity<ResponseTemplate<MemberResponseMessage>> signUp(@RequestBody MemberJoinRequest memberJoinRequest) {

        MemberJoinResponse response = memberServiceImpl.signUp(memberJoinRequest);

        return new ResponseEntity<>(
                ResponseTemplate.<MemberResponseMessage>builder()
                        .result(true)
                        .msg(MemberResponseMessage.MEMBER_LOGIN_SUCCESS.getMessage())
                        .build()
                , HttpStatus.OK);
    }

}
