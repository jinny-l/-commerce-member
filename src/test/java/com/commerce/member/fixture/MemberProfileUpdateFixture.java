package com.commerce.member.fixture;

import com.commerce.member.api.dto.MemberProfileUpdate;

public enum MemberProfileUpdateFixture {

    정상_수정_정보(
            "password",
            "이커머스",
            "01012345672",
            "commerce99@commerce.com"
    ),
    정상_회원1과_중복된_이메일_수정_정보(
            "password1",
            "이커머스",
            "01012345679",
            MemberCreateFixture.정상_정보_회원1.email
    ),
    정상_회원1과_중복된_닉네임_수정_정보(
            "password1",
            MemberCreateFixture.정상_정보_회원1.nickname,
            "01012345679",
            "commerce1@commerce.com"
    );

    private final String password;
    private final String nickname;
    private final String phoneNumber;
    private final String email;

    MemberProfileUpdateFixture(String password, String nickname, String phoneNumber, String email) {
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public MemberProfileUpdate.Request toDto() {
        return MemberProfileUpdate.Request.builder()
                .password(password)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }
}
