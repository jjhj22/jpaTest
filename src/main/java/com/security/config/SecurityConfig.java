package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.validation.constraints.Size;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        // 토큰인증, 클라이언트와 서버의 상호 작용으로 사용, 정상적인 경로로 요청이 이루어지기 위해
        // 스프링시큐리티에서 제공하는 값이다
        // disable은 csrf 토큰을 사용하지 않겠다로 적용
        http.csrf().disable();

        return http.build();
    }
}
