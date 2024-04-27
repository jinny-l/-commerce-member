package com.commerce.member.api.dto;

import com.commerce.member.api.domain.Member;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberProfileUpdate {

    @Getter
    public static class Request {

        @NotBlank(message = "비밀번호는 최소 8자 입력해주세요.")
        @Size(min = 8, max = 100)
        private String password;

        @NotBlank(message = "닉네임은 최대 20자까지 입력 가능합니다.")
        @Size(max = 20)
        private String nickname;

        @NotBlank(message = "전화번호는 최대 20자까지 입력 가능합니다.")
        @Size(max = 20)
        private String phoneNumber;

        @NotBlank(message = "이메일은 최대 320자까지 입력 가능합니다.")
        @Email
        @Size(max = 320)
        private String email;
    }

    @Getter
    public static class Response {

        private final String nickname;
        private final String phoneNumber;
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
