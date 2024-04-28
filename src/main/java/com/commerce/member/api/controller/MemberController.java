package com.commerce.member.api.controller;

import com.commerce.member.api.dto.MemberCreateRequest;
import com.commerce.member.api.dto.MemberProfileUpdate;
import com.commerce.member.api.dto.MembersReadResponse;
import com.commerce.member.api.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "회원 기능 API Controller")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "회원 가입 API",
            responses = {
                    @ApiResponse(responseCode = "201", description = "가입 성공"),
                    @ApiResponse(responseCode = "400", description = "이메일 중복 등의 이유로 가입 실패")
            }
    )
    @PostMapping("/api/user/join")
    public ResponseEntity<Void> join(
            @Valid @RequestBody MemberCreateRequest request
    ) {
        memberService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Operation(
            summary = "회원 목록 조회 API",
            parameters = {

            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "조회 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 정렬 옵션으로 조회 실패")
            }
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지 번호(미입력 시 기본값 0)"),
            @ApiImplicitParam(name = "pageSize", value = "한 페이지에 표시될 수 있는 최대 회원 수(미입력 시 기본 값 10)"),
            @ApiImplicitParam(name = "sort", value = "정렬 옵션(미입력 시 기본 값 name) - createdTime, name")
    })
    @GetMapping("/api/user/list")
    public ResponseEntity<MembersReadResponse> readAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "name") String sort
    ) {
        MembersReadResponse response = memberService.findAllBy(page, pageSize, sort);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "회원 정보 수정 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "수정 성공"),
                    @ApiResponse(responseCode = "400", description = "이메일 중복 등의 이유로 수정 실패")
            }
    )
    @PatchMapping("/api/user/{loginId}")
    public ResponseEntity<MemberProfileUpdate.Response> updateProfile(
            @PathVariable String loginId,
            @Valid @RequestBody MemberProfileUpdate.Request request
    ) {
        MemberProfileUpdate.Response response = memberService.updateProfile(loginId, request);

        return ResponseEntity.ok(response);
    }
}
