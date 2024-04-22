package com.commerce.member.api.repository;

import com.commerce.member.api.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
