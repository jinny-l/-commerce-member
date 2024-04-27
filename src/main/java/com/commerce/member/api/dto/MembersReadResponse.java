package com.commerce.member.api.dto;

import com.commerce.member.api.domain.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@ApiModel(value = "회원 목록 조회 API - 응답")
@Getter
public class MembersReadResponse {

    @Schema(description = "전체 페이지 수", example = "20")
    private final int totalPages;

    @Schema(description = "회원 목록")
    private final List<MemberReadResponse> members;

    private MembersReadResponse(int totalPages, List<MemberReadResponse> members) {
        this.totalPages = totalPages;
        this.members = members;
    }

    public static MembersReadResponse from(int totalPages, List<Member> members) {
        return new MembersReadResponse(
                totalPages,
                members.stream()
                        .map(MemberReadResponse::from)
                        .collect(Collectors.toList())
        );
    }

    @Getter
    private static class MemberReadResponse {

        @Schema(description = "회원 식별자", example = "1")
        private final Long id;

        @Schema(description = "로그인 ID", example = "commerce")
        private final String loginId;

        @Schema(description = "이름", example = "김커머스")
        private final String name;

        @Builder
        private MemberReadResponse(Long id, String loginId, String name) {
            this.id = id;
            this.loginId = loginId;
            this.name = name;
        }

        private static MemberReadResponse from(Member member) {
            return MemberReadResponse.builder()
                    .id(member.getId())
                    .loginId(member.getLoginId())
                    .name(member.getName())
                    .build();
        }
    }
}

