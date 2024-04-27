package com.commerce.member.test;

import com.commerce.global.RestAssuredTest;
import com.commerce.member.api.dto.MemberCreateRequest;
import com.commerce.member.fixture.MemberCreateFixture;
import com.commerce.member.step.MemberStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("[인수 테스트] 회원 가입 API")
class MemberCreateTest extends RestAssuredTest {

    @Test
    void 정상정보로_회원_가입하면_성공한다() {
        // given
        MemberCreateRequest request = MemberCreateFixture.정상_정보_회원.toDto();

        // when
        ExtractableResponse<Response> response = MemberStep.회원_생성_요청(request);

        // then
        응답_상태코드_검증(response, HttpStatus.CREATED);
    }

    @Test
    void 로그인ID가_중복일_경우_회원가입에_실패한다() {
        // given
        MemberStep.회원_생성_요청(MemberCreateFixture.정상_정보_회원.toDto());
        MemberCreateRequest duplicatedLoginIdRequest = MemberCreateFixture.중복_로그인Id_회원.toDto();

        // when
        ExtractableResponse<Response> response = MemberStep.회원_생성_요청(duplicatedLoginIdRequest);

        // then
        응답_상태코드_검증(response, HttpStatus.BAD_REQUEST);
    }

    @Test
    void 이메일이_중복일_경우_회원가입에_실패한다() {
        // given
        MemberStep.회원_생성_요청(MemberCreateFixture.정상_정보_회원.toDto());
        MemberCreateRequest duplicatedEmailRequest = MemberCreateFixture.중복_이메일_회원.toDto();

        // when
        ExtractableResponse<Response> response = MemberStep.회원_생성_요청(duplicatedEmailRequest);

        // then
        응답_상태코드_검증(response, HttpStatus.BAD_REQUEST);
    }

    @Test
    void 닉네임_중복일_경우_회원가입에_실패한다() {
        // given
        MemberStep.회원_생성_요청(MemberCreateFixture.정상_정보_회원.toDto());
        MemberCreateRequest duplicatedNicknameRequest = MemberCreateFixture.중복_닉네임_회원.toDto();

        // when
        ExtractableResponse<Response> response = MemberStep.회원_생성_요청(duplicatedNicknameRequest);

        // then
        응답_상태코드_검증(response, HttpStatus.BAD_REQUEST);
    }
}
