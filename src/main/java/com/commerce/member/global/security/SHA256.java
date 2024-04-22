package com.commerce.member.global.security;

import com.commerce.member.global.exception.ApiException;
import com.commerce.member.global.security.exception.SecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class SHA256 implements PasswordEncoder {

    private static final String algorithm = "SHA-256";

    @Override
    public String encrypt(final String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] digest = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(digest);

        } catch (NoSuchAlgorithmException e) {
            throw new ApiException(SecurityException.INVALID_ALGORITHM);
        }
    }
}
