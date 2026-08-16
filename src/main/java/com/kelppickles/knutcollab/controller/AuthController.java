package com.kelppickles.knutcollab.controller;

import com.kelppickles.knutcollab.dto.UserLoginRequest;
import com.kelppickles.knutcollab.response.LoginResponse;
import com.kelppickles.knutcollab.service.JwtProvider;
import com.kelppickles.knutcollab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody UserLoginRequest request
            ) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        String token = jwtProvider.createToken(
                authentication.getName()
        );

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
