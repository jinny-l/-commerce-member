package com.commerce.member.api.service;

import com.commerce.member.api.domain.Member;
import com.commerce.member.api.domain.SortType;
import com.commerce.member.api.dto.MemberCreateRequest;
import com.commerce.member.api.dto.MemberProfileUpdate;
import com.commerce.member.api.dto.MembersReadResponse;
import com.commerce.member.api.exception.MemberException;
import com.commerce.member.api.repository.MemberRepository;
import com.commerce.member.global.exception.ApiException;
import com.commerce.member.global.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(MemberCreateRequest request) {
        validateLoginId(request.getLoginId());
        validateEmail(request.getEmail());
        validateNickName(request.getNickname());

        String encryptedPassword = passwordEncoder.encrypt(request.getPassword());

        memberRepository.save(request.toEntity(encryptedPassword));
    }

    public MembersReadResponse findAllBy(int page, int pageSize, String sort) {
        SortType sortType = SortType.from(sort);

        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(sortType.getText()));
        Page<Member> members = memberRepository.findAllBy(pageRequest);

        return MembersReadResponse.from(
                members.getTotalPages(),
                members.getContent()
        );
    }

    @Transactional
    public MemberProfileUpdate.Response updateProfile(String loginId, MemberProfileUpdate.Request request) {
        validateEmail(request.getEmail());
        validateNickName(request.getNickname());

        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new ApiException(MemberException.LOGIN_ID_NOT_FOUND));

        String encryptedPassword = passwordEncoder.encrypt(request.getPassword());

        member.update(
                encryptedPassword,
                request.getNickname(),
                request.getPhoneNumber(),
                request.getEmail()
        );

        return MemberProfileUpdate.Response.from(member);
    }

    private void validateLoginId(String loginId) {
        if (memberRepository.existsByLoginId(loginId)) {
            throw new ApiException(MemberException.DUPLICATED_LOGIN_ID);
        }
    }

    private void validateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new ApiException(MemberException.DUPLICATED_EMAIL);
        }
    }

    private void validateNickName(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new ApiException(MemberException.DUPLICATED_NICKNAME);
        }
    }
}
