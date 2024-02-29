package com.todayfriend.member.service;

import com.todayfriend.global.auth.jwt.JwtTokenProvider;
import com.todayfriend.global.exception.validator.MemberValidator;
import com.todayfriend.member.domain.Member;
import com.todayfriend.member.dto.LoginDto;
import com.todayfriend.member.dto.TokenDto;
import com.todayfriend.member.dto.request.MemberJoinRequest;
import com.todayfriend.member.dto.request.MemberLoginRequest;
import com.todayfriend.member.dto.response.MemberJoinResponse;
import com.todayfriend.member.enums.Role;
import com.todayfriend.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberValidator memberValidator;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, String> redisTemplate;
    @Override
    @Transactional
    public MemberJoinResponse signUp(MemberJoinRequest memberJoinRequest) {
        Optional<Member> findMember = memberRepository.findByEmail(memberJoinRequest.getEmail());
        memberValidator.checkDuplicate(findMember);
        log.info("이메일",memberJoinRequest.getEmail());
        Member saveMember = memberRepository.save(Member.builder()
                .email(memberJoinRequest.getEmail())
                .password(passwordEncoder.encode(memberJoinRequest.getPassword()))
                .name(memberJoinRequest.getName())
                .nickname(UUID.randomUUID().toString())
                .role(Role.USER)
                .build());

        return MemberJoinResponse.builder()
                .email(saveMember.getEmail())
                .build();

    }

    @Override
    @Transactional
    public TokenDto login(MemberLoginRequest memberLoginRequest) {

        Optional<Member> findMember = memberRepository.findByEmail(memberLoginRequest.getEmail());
        memberValidator.checkEmail(findMember);
        memberValidator.checkPassword(passwordEncoder.matches(memberLoginRequest.getPassword(), findMember.get().getPassword()));
        String accessToken = jwtTokenProvider.createAccessToken(findMember.get().getId(), findMember.get().getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(findMember.get().getId());
        jwtTokenProvider.storeRefreshToken(findMember.get().getId(), refreshToken);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    @Transactional
    public TokenDto reissue(String refreshToken) {
        log.info("토큰 재발급 서비스 진입!");
        Claims claims = jwtTokenProvider.parseJwtToken(refreshToken);

        Optional<Member> findMember = memberRepository.findById(Integer.parseInt(claims.getId()));
        memberValidator.checkEmail(findMember);
        String redisRefreshToken = redisTemplate.opsForValue().get(Integer.toString(findMember.get().getId()));
        memberValidator.checkEqualToken(refreshToken.substring("Bearer ".length()), redisRefreshToken);

        String newAccessToken = jwtTokenProvider.createAccessToken(findMember.get().getId(), findMember.get().getEmail());
        String newRefreshToken = jwtTokenProvider.createRefreshToken(findMember.get().getId());
        jwtTokenProvider.storeRefreshToken(findMember.get().getId(), newRefreshToken);

        return TokenDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

}
