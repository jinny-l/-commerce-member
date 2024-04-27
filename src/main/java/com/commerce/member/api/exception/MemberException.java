package com.commerce.member.api.exception;

import com.commerce.member.global.exception.CustomException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberException implements CustomException {

    DUPLICATED_LOGIN_ID(HttpStatus.BAD_REQUEST, "이미 가입된 ID입니다."),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입된 이메일입니다."),
    DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "닉네임이 이미 존재합니다."),
    LOGIN_ID_NOT_FOUND(HttpStatus.BAD_REQUEST, "잘못된 로그인 ID입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
