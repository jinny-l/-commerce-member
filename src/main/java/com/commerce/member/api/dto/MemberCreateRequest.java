package com.commerce.member.api.dto;

import com.commerce.member.api.domain.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@ApiModel(value = "회원 가입 API - 요청")
@Getter
public class MemberCreateRequest {

    @Schema(description = "로그인 ID", example = "commerce")
    @NotBlank(message = "ID는 최대 20까지 입력 가능합니다.")
    @Size(max = 20)
    private String loginId;

    @Schema(description = "로그인 비밀번호", example = "12345678")
    @NotBlank(message = "비밀번호는 최소 8자 입력해주세요.")
    @Size(min = 8, max = 100)
    private String password;

    @Schema(description = "로그인 비밀번호", example = "닉네임")
    @NotBlank(message = "닉네임은 최대 20자까지 입력 가능합니다.")
    @Size(max = 20)
    private String nickname;

    @Schema(description = "이름", example = "김커머스")
    @NotBlank(message = "이름은 최대 20자까지 입력 가능합니다.")
    @Size(max = 20)
    private String name;

    @Schema(description = "전화번호", example = "01012345678")
    @NotBlank(message = "전화번호는 최대 20자까지 입력 가능합니다.")
    @Size(max = 20)
    private String phoneNumber;

    @Schema(description = "이메일", example = "commerce@commerce.com")
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
