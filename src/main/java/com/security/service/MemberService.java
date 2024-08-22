package com.security.service;

import com.security.dto.MemberDto;
import com.security.entity.Member;
import com.security.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void 회원가입처리(@Valid MemberDto memberDto) {
        Member member = memberDto.createEntity();
        memberRepository.save(member);
    }
}
