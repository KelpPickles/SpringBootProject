package com.kelppickles.knutcollab.filter;

import com.kelppickles.knutcollab.service.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Authorization 헤더 가져오기
        String authorization = request.getHeader("Authorization");

        // null 이거나, Bearer(토큰 소지자)가 아니라면
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            // 아무것도 하지 말고 다음 필터에게 넘기기
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 추출('Bearer '를 제외하기 위해 7부터)
        String token = authorization.substring(7);

        // 추출한 토큰에서 이메일 파싱
        String email = jwtProvider.getEmailFromToken(token);

        // 서명이 올바른지 확인 (Spring Security가 이해 가능한 인증 객체 생성)
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        List.of(new SimpleGrantedAuthority("USER"))
                );

        // Security Context에 저장해, Spring Security가 해당 요청을 인증된 요청으로 취급하도록 함
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }
}
