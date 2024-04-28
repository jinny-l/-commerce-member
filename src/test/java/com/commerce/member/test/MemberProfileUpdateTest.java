package com.commerce.member.test;

import com.commerce.global.RestAssuredTest;
import com.commerce.member.api.dto.MemberProfileUpdate;
import com.commerce.member.fixture.MemberCreateFixture;
import com.commerce.member.fixture.MemberProfileUpdateFixture;
import com.commerce.member.step.MemberStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("[인수 테스트] 회원 정보 수정 API")
class MemberProfileUpdateTest extends RestAssuredTest {

    @Test
    void 정상정보로_회원_정보_수정_요청_시_성공한다() {
        // given
        MemberStep.회원_생성_요청(MemberCreateFixture.정상_정보_회원1.toDto());

        MemberProfileUpdate.Request request = MemberProfileUpdateFixture.정상_수정_정보.toDto();

        // when
        ExtractableResponse<Response> response = MemberStep.회원_정보_수정_요청(
                request,
                MemberCreateFixture.정상_정보_회원1.loginId
        );

        // then
        응답_상태코드_검증(response, HttpStatus.OK);
    }

    @Test
    void 수정된_이메일이_중복일_경우_정보수정에_실패한다() {
        // given
        MemberStep.회원_생성_요청(MemberCreateFixture.정상_정보_회원1.toDto());
        MemberStep.회원_생성_요청(MemberCreateFixture.정상_정보_회원2.toDto());

        MemberProfileUpdate.Request duplicatedEmailRequest = MemberProfileUpdateFixture.정상_회원1과_중복된_이메일_수정_정보.toDto();

        // when
        ExtractableResponse<Response> response = MemberStep.회원_정보_수정_요청(
                duplicatedEmailRequest,
                MemberCreateFixture.정상_정보_회원2.loginId
        );

        // then
        응답_상태코드_검증(response, HttpStatus.BAD_REQUEST);
    }

    @Test
    void 수정된_닉네임이_중복일_경우_정보수정에_실패한다() {
        // given
        MemberStep.회원_생성_요청(MemberCreateFixture.정상_정보_회원1.toDto());
        MemberStep.회원_생성_요청(MemberCreateFixture.정상_정보_회원2.toDto());

        MemberProfileUpdate.Request duplicatedNicknameRequest = MemberProfileUpdateFixture.정상_회원1과_중복된_닉네임_수정_정보.toDto();

        // when
        ExtractableResponse<Response> response = MemberStep.회원_정보_수정_요청(
                duplicatedNicknameRequest,
                MemberCreateFixture.정상_정보_회원2.loginId
        );


        // then
        응답_상태코드_검증(response, HttpStatus.BAD_REQUEST);
    }
}
