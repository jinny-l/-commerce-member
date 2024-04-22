package com.commerce.member.global.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApiException extends RuntimeException {

    private final CustomException customException;

    @Override
    public String getMessage() {
        return customException.getMessage();
    }

    public int getStatus() {
        return customException.getHttpStatus().value();
    }
}
