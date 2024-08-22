package com.security.config;

import com.security.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.validation.constraints.Size;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Autowired
    MemberService memberService;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {



        // .authorizeRequests() 인가 규칙
        // anyRequest().authenticated() 모든 요청에 대해 인증을 요구

        http.authorizeRequests()
                .mvcMatchers("/signUp").permitAll() // signUp 주소는 인증없이 허용
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/signIn") // 로그인 페이지 요청 주소 - 커스텀 페이지로 처리
                .loginProcessingUrl("/login_chk") // 로그인 처리 주소
                .defaultSuccessUrl("/",true) //로그인 성공시 이동할 페이지
                .usernameParameter("id") // login.html의 아이디 입력 input 태그 값
                .passwordParameter("pw") // login.html의 비밀번호 입력 input 태그 값
                .permitAll()
                        .and()
                                .logout().logoutUrl("/logout")//로그아웃하기위해 /logout주소요청
                .logoutSuccessUrl("/")//로그아웃 성공시 이동할 주소
                .invalidateHttpSession(true)//세션제거
                .permitAll();
        //http.formLogin().disable(); //기본 로그인 페이지 비활성화
        // 토큰인증, 클라이언트와 서버의 상호 작용으로 사용, 정상적인 경로로 요청이 이루어지기 위해
        // 스프링시큐리티에서 제공하는 값이다
        // disable은 csrf 토큰을 사용하지 않겠다로 적용
        http.csrf().disable();

        return http.build();
    }

    //비밀번호를 해시하여 암호화 하는 도구
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
