package com.commerce.member.global.security;

public interface PasswordEncoder {

    String encrypt(String password);
}
