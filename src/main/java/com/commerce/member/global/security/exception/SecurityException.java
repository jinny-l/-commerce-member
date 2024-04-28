package com.commerce.member.global.security.exception;

import com.commerce.member.global.exception.CustomException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SecurityException implements CustomException {

    INVALID_ALGORITHM(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
