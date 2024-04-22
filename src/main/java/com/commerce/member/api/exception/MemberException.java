package com.commerce.member.api.exception;

import com.commerce.member.global.exception.CustomException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberException implements CustomException {

    DUPLICATED_LOGIN_ID(HttpStatus.UNAUTHORIZED, "이미 가입된 ID입니다."),
    DUPLICATED_EMAIL(HttpStatus.UNAUTHORIZED, "이미 가입된 이메일입니다."),
    DUPLICATED_NICKNAME(HttpStatus.UNAUTHORIZED, "닉네임이 이미 존재합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
