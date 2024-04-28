package com.commerce.member.api.dto;

import com.commerce.member.api.domain.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberProfileUpdate {

    @ApiModel(value = "회원 정보 수정 API - 요청")
    @Getter
    public static class Request {

        @Schema(description = "비밀번호", example = "123456789")
        @NotBlank(message = "비밀번호는 최소 8자 입력해주세요.")
        @Size(min = 8, max = 100)
        private String password;

        @Schema(description = "닉네임", example = "commerce1")
        @NotBlank(message = "닉네임은 최대 20자까지 입력 가능합니다.")
        @Size(max = 20)
        private String nickname;

        @Schema(description = "로그인 ID", example = "01012345679")
        @NotBlank(message = "전화번호는 최대 20자까지 입력 가능합니다.")
        @Size(max = 20)
        private String phoneNumber;

        @Schema(description = "이메일", example = "commerce1@commerce.com")
        @NotBlank(message = "이메일은 최대 320자까지 입력 가능합니다.")
        @Email
        @Size(max = 320)
        private String email;

        @Builder
        private Request(String password, String nickname, String phoneNumber, String email) {
            this.password = password;
            this.nickname = nickname;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }
    }

    @ApiModel(value = "회원 정보 수정 API - 응답")
    @Getter
    public static class Response {

        @Schema(description = "닉네임", example = "commerce1")
        private final String nickname;

        @Schema(description = "로그인 ID", example = "01012345679")
        private final String phoneNumber;

        @Schema(description = "이메일", example = "commerce1@commerce.com")
        private final String email;

        @Builder
        private Response(String nickname, String phoneNumber, String email) {
            this.nickname = nickname;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        public static Response from(Member member) {
            return Response.builder()
                    .nickname(member.getNickname())
                    .phoneNumber(member.getPhoneNumber())
                    .email(member.getEmail())
                    .build();
        }
    }
}
