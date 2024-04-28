package com.commerce.member.step;

import com.commerce.global.RestAssuredTest;
import com.commerce.member.api.dto.MemberCreateRequest;
import com.commerce.member.api.dto.MemberProfileUpdate;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class MemberStep extends RestAssuredTest {

    public static ExtractableResponse<Response> 회원_생성_요청(MemberCreateRequest request) {
        return givenJsonRequest()
                .body(request)
                .when().post("/api/user/join")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 회원_정보_수정_요청(MemberProfileUpdate.Request request, String loginId) {
        return givenJsonRequest()
                .body(request)
                .when().patch("/api/user/{loginId}", loginId)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 회원_목록_조회_요청(Integer page, Integer pageSize, String sort) {
        return givenJsonRequest()
                .param("page", page)
                .param("pageSize", pageSize)
                .param("sort", sort)
                .when().get("/api/user/list")
                .then().log().all().extract();
    }
}
