package com.kelppickles.knutcollab.config;

import com.kelppickles.knutcollab.filter.JwtAuthenticationFilter;
import com.kelppickles.knutcollab.service.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration      // Spring 설정 클래스임 (Security, Bean 등록, CORS, ...)
@EnableWebSecurity  // Spring Security를 활성화한다.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtProvider jwtProvider) throws Exception {
        // 보안 규칙 목록 (ex. POST /users -> 허용)

        // 임시(테스트용)
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> {
            // Http 요청에 대한 권한 설정
            auth.requestMatchers(HttpMethod.POST,
                            "/users",
                            "/auth/login") // /users에 대한 POST 요청 지정
                    .permitAll()    // 해당 경로에 대해 누구나 접근 가능.
                    .anyRequest().authenticated();  // 위 요청을 제외한 나머지는 모두 로그인 필요
        });

        http.addFilterBefore(
                // UsernamePasswordAuthenticationFilter 이전에 JwtAuthenticationFilter를 실행
                new JwtAuthenticationFilter(jwtProvider),
                UsernamePasswordAuthenticationFilter.class
        );

        // 위 보안 규칙을 SecurityFilterChain 객체로 만들어 반환.
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
