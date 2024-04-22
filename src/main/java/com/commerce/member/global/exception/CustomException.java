package com.commerce.member.global.exception;

import org.springframework.http.HttpStatus;

public interface CustomException {

    HttpStatus getHttpStatus();

    String getMessage();
}
