package com.commerce.member.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.commerce.global.RestAssuredTest;
import com.commerce.member.api.domain.SortType;
import com.commerce.member.api.dto.MembersReadResponse;
import com.commerce.member.step.MemberStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

@DisplayName("[인수 테스트] 회원 목록 조회 API")
class MembersReadTest extends RestAssuredTest {

    @Test
    @Sql("classpath:members.sql")
    void 가입일순_회원_목록_조회_요청_시_성공한다() {
        // when
        ExtractableResponse<Response> response = MemberStep.회원_목록_조회_요청(0, 10, SortType.CREATED_TIME.getText());

        // then
        가입일순_회원_목록_조회_응답_정보_검증(response);
        응답_상태코드_검증(response, HttpStatus.OK);
    }

    private void 가입일순_회원_목록_조회_응답_정보_검증(ExtractableResponse<Response> response) {
        MembersReadResponse actual = response.jsonPath().getObject(".", MembersReadResponse.class);

        assertAll(
                () -> assertThat(actual)
                        .hasFieldOrPropertyWithValue("totalPages", 2)
                        .hasFieldOrProperty("members"),
                () -> assertThat(actual.getMembers()).hasSize(10),
                () -> assertThat(actual.getMembers().get(0).getCreatedTime())
                        .isBefore(actual.getMembers().get(1).getCreatedTime())
        );
    }

    @Test
    @Sql("classpath:members.sql")
    void 이름순_회원_목록_조회_요청_시_성공한다() {
        // when
        ExtractableResponse<Response> response = MemberStep.회원_목록_조회_요청(0, 5, SortType.NAME.getText());

        // then
        이름순_회원_목록_조회_응답_정보_검증(response);
        응답_상태코드_검증(response, HttpStatus.OK);
    }

    private void 이름순_회원_목록_조회_응답_정보_검증(ExtractableResponse<Response> response) {
        MembersReadResponse actual = response.jsonPath().getObject(".", MembersReadResponse.class);

        assertAll(
                () -> assertThat(actual)
                        .hasFieldOrPropertyWithValue("totalPages", 4)
                        .hasFieldOrProperty("members"),
                () -> assertThat(actual.getMembers()).hasSize(5),
                () -> assertThat(actual.getMembers().get(0).getName())
                        .isLessThan(actual.getMembers().get(1).getName())
        );
    }

    @Test
    void 쿼리_스트링이_없어도_기본값으로_조회에_성공한다() {
        // when
        ExtractableResponse<Response> response1 = MemberStep.회원_목록_조회_요청(null, null, null);
        ExtractableResponse<Response> response2 = MemberStep.회원_목록_조회_요청(null, null, "");

        // then
        assertAll(
                () -> 응답_상태코드_검증(response1, HttpStatus.OK),
                () -> 응답_상태코드_검증(response2, HttpStatus.OK)
        );
    }

    @Test
    void 잘못된_정렬_옵션으로_조회_요청_시_실패한다() {
        // when
        ExtractableResponse<Response> response = MemberStep.회원_목록_조회_요청(1, 10, "error");

        // then
        응답_상태코드_검증(response, HttpStatus.BAD_REQUEST);
    }
}
