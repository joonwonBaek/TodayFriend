package com.todayfriend.member.controller;

import com.todayfriend.global.auth.jwt.JwtTokenProvider;
import com.todayfriend.global.util.ResponseTemplate;
import com.todayfriend.member.dto.LoginDto;
import com.todayfriend.member.dto.TokenDto;
import com.todayfriend.member.dto.request.MemberJoinRequest;
import com.todayfriend.member.dto.request.MemberLoginRequest;
import com.todayfriend.member.dto.response.MemberJoinResponse;
import com.todayfriend.member.enums.MemberResponseMessage;
import com.todayfriend.member.service.MemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mebers", description = "멤버 관련 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;


    @Operation(summary = "회원 가입", description = "회원가입(아이디, 비밀번호) 입력")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "1000", description = "중복된 아이디입니다", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/join")
    public ResponseEntity<ResponseTemplate<MemberResponseMessage>> signUp(@RequestBody MemberJoinRequest memberJoinRequest) {

        MemberJoinResponse response = memberServiceImpl.signUp(memberJoinRequest);

        return new ResponseEntity<>(
                ResponseTemplate.<MemberResponseMessage>builder()
                        .result(true)
                        .msg(MemberResponseMessage.MEMBER_JOIN_SUCCESS.getMessage())
                        .build()
                , HttpStatus.OK);
    }
    @Operation(summary = "로그인", description = "로그인(아이디, 비밀번호) 입력 성공하면 엑세스와 리프레쉬 토큰 발급")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "1001", description = "회원정보가 없습니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "1002", description = "비밀번호가 일치하지 않습니다", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/login")
    public ResponseEntity<ResponseTemplate<TokenDto>> login(@RequestBody MemberLoginRequest memberLoginRequest) {

        TokenDto tokenDto = memberServiceImpl.login(memberLoginRequest);

        return new ResponseEntity<>(
                ResponseTemplate.<TokenDto>builder()
                        .result(true)
                        .msg(MemberResponseMessage.MEMBER_LOGIN_SUCCESS.getMessage())
                        .data(tokenDto)
                        .build(),
                HttpStatus.OK);

    }

    @Operation(summary = "access&refresh 토큰 재발급", description = "access토큰 만료되면 refresh 토큰을 이용하여 재발급하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "리프레쉬 토큰이 일치하지 않습니다", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/reissue")
    public ResponseEntity<ResponseTemplate<TokenDto>> reissue(@RequestHeader(value = "Authorization-Refresh") String refreshToken) {


        TokenDto tokenDto = memberServiceImpl.reissue(refreshToken);


        return new ResponseEntity<>(
                ResponseTemplate.<TokenDto>builder()
                        .result(true)
                        .msg(MemberResponseMessage.MEMBER_REISSUE_SUCCESS.getMessage())
                        .data(tokenDto)
                        .build(),
                HttpStatus.OK);
    }
}
