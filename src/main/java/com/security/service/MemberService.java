package com.security.service;

import com.security.dto.MemberDto;
import com.security.entity.Member;
import com.security.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class MemberService implements MemberInterface {
    @Autowired
    private MemberRepository memberRepository;

    public void 회원가입처리(@Valid MemberDto memberDto, PasswordEncoder passwordEncoder) {

        Member member = memberDto.createEntity(passwordEncoder);
        memberRepository.save(member);
    }
    // 스프링 시큐리티에서 로그인 처리할 때 사용되는 메서드
    // 커스텀 로그인 방식으로 하는 경우 서비스크래스에서 구현한다
    // loadUserByUsername 메서드에 매개변수는 로그인 아이디만 받는다
    // 그래서 회원가입시 아이디가 중복 저장되지 않도록 해줘야 한다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(username);
        if(member == null) { //로그인을 위해 입력한 아이디가 잘못된 경우
            throw new UsernameNotFoundException(username);
        }
        return User.builder().username(member.getUserId()).password(member.getPassword())
                .roles("USER")
                .build();

    }

//    public boolean 로그인처리(String userId, String password) {
//       Member member = memberRepository.findByUserIdAndPassword(userId, password);
//       if(member != null) {
//           return true;
//
//       }
//        return false;
//    }
}
