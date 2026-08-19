package com.kelppickles.knutcollab.controller;

import com.kelppickles.knutcollab.entity.User;
import com.kelppickles.knutcollab.response.UserResponse;
import com.kelppickles.knutcollab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeController {
    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getMe() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userService.getUserByEmail(email);

        return UserResponse.from(user);
    }
}
