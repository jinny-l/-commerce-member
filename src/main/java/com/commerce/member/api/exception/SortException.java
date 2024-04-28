package com.commerce.member.api.exception;

import com.commerce.member.global.exception.CustomException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SortException implements CustomException {

    INVALID_SORT_TYPE(HttpStatus.BAD_REQUEST, "잘못된 정렬 옵션입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
