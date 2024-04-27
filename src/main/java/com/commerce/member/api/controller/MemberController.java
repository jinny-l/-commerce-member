package com.commerce.member.api.controller;

import com.commerce.member.api.dto.MemberCreateRequest;
import com.commerce.member.api.dto.MemberProfileUpdate;
import com.commerce.member.api.dto.MembersReadResponse;
import com.commerce.member.api.service.MemberService;
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

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/user/join")
    public ResponseEntity<Void> join(
            @Valid @RequestBody MemberCreateRequest request
    ) {
        memberService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/api/user/list")
    public ResponseEntity<MembersReadResponse> readAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "name") String sort
    ) {
        MembersReadResponse response = memberService.findAllBy(page, pageSize, sort);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/user/{loginId}")
    public ResponseEntity<MemberProfileUpdate.Response> updateProfile(
            @PathVariable String loginId,
            @Valid @RequestBody MemberProfileUpdate.Request request
    ) {
        MemberProfileUpdate.Response response = memberService.updateProfile(loginId, request);

        return ResponseEntity.ok(response);
    }
}