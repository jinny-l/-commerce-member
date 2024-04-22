package com.commerce.member.api.dto;

import com.commerce.member.api.domain.Member;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberCreateRequest {

    @NotBlank(message = "ID는 최대 20까지 입력 가능합니다.")
    @Size(max = 20)
    private String loginId;

    @NotBlank(message = "비밀번호는 최소 8자 입력해주세요.")
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank(message = "닉네임은 최대 20자까지 입력 가능합니다.")
    @Size(max = 20)
    private String nickname;

    @NotBlank(message = "이름은 최대 20자까지 입력 가능합니다.")
    @Size(max = 20)
    private String name;

    @NotBlank(message = "전화번호는 최대 20자까지 입력 가능합니다.")
    @Size(max = 20)
    private String phoneNumber;

    @NotBlank(message = "이메일은 최대 320자까지 입력 가능합니다.")
    @Email
    @Size(max = 320)
    private String email;

    public Member toEntity(String encryptedPassword) {
        return Member.builder()
                .loginId(loginId)
                .password(encryptedPassword)
                .nickname(nickname)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }
}
