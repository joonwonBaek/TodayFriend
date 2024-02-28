package com.todayfriend.global.exception.validator;

import com.todayfriend.global.exception.CustomException;
import com.todayfriend.global.exception.message.MemberErrorEnum;
import com.todayfriend.member.domain.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class MemberValidator {
    public void checkDuplicate(Optional<Member> findLoginId) {
        if (findLoginId.isPresent()) {
            throw CustomException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .code(MemberErrorEnum.MEMBER_ERROR_JOIN_DUPLICATE.getCode())
                    .message(MemberErrorEnum.MEMBER_ERROR_JOIN_DUPLICATE.getMessage())
                    .build();
        }
    }
}
