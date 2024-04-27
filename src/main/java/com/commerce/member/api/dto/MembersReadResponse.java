package com.commerce.member.api.dto;

import com.commerce.member.api.domain.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MembersReadResponse {

    private final int totalPages;
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
    public static class MemberReadResponse {

        private final Long id;
        private final String loginId;
        private final String name;

        @Builder
        private MemberReadResponse(Long id, String loginId, String name) {
            this.id = id;
            this.loginId = loginId;
            this.name = name;
        }

        public static MemberReadResponse from(Member member) {
            return MemberReadResponse.builder()
                    .id(member.getId())
                    .loginId(member.getLoginId())
                    .name(member.getName())
                    .build();
        }
    }
}

