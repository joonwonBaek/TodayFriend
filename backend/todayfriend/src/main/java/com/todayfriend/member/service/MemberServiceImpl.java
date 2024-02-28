package com.todayfriend.member.service;

import com.todayfriend.global.exception.validator.MemberValidator;
import com.todayfriend.member.domain.Member;
import com.todayfriend.member.dto.request.MemberJoinRequest;
import com.todayfriend.member.dto.response.MemberJoinResponse;
import com.todayfriend.member.enums.Role;
import com.todayfriend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    @Transactional
    public MemberJoinResponse signUp(MemberJoinRequest memberJoinRequest) {
        Optional<Member> findMember = memberRepository.findByEmail(memberJoinRequest.getEmail());
        memberValidator.checkDuplicate(findMember);
        log.info("이메일",memberJoinRequest.getEmail());
        Member saveMember = memberRepository.save(Member.builder()
                .email(memberJoinRequest.getEmail())
                .password(passwordEncoder.encode(memberJoinRequest.getPassword()))
                .name("신준호")
                .nickname(UUID.randomUUID().toString())
                .role(Role.USER)
                .build());

        return MemberJoinResponse.builder()
                .email(saveMember.getEmail())
                .build();

    }
}
