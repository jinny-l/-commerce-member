package com.commerce.unit.security;

import static org.assertj.core.api.Assertions.assertThat;

import com.commerce.member.global.security.SHA256;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[단위 테스트] SHA256")
public class PasswordEncoderTest {

    private static final SHA256 sha256 = new SHA256();

    @Test
    @DisplayName("암호화에 성공한다.")
    void encrypt() {
        // given
        String password = "12345678";

        // when
        String encryptedPassword = sha256.encrypt(password);

        // then
        assertThat(encryptedPassword).isNotEqualTo(password);
    }
}
