package com.security.dto;

import com.security.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberDto {
    @NotBlank(message = "아이디는 필수입니다")
    private String userId; //아이디

    @Size(min=4, max=10, message = "비밀번호는 4~10자리 구성입니다")
    private String password; //비밀번호

    @Min(value = 13, message = "13세 이상만 가입 가능합니다")
    @Max(value = 80, message = "80세 이하만 가입 가능합니다")
    private int age; // 나이

    //DTO -> Entity
    public Member createEntity(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setUserId(this.userId);

        String pw = passwordEncoder.encode(this.password); // 회원가입시 입력한 비밀번호 암호화
        member.setPassword(pw);
        member.setAge(this.age);
        return member;

    }

    //Entity -> Dto
    public static MemberDto of(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(member.getUserId());
        memberDto.setPassword(member.getPassword());
        memberDto.setAge(member.getAge());
        return memberDto;
    }
}
