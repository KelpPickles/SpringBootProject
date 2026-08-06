package com.kelppickles.knutcollab.controller;

import com.kelppickles.knutcollab.service.UserService;
import com.kelppickles.knutcollab.dto.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public void createUser(@RequestBody UserCreateRequest request) {
        // @RequestBody : 요청으로 들어온 Body를 Java 객체로 바꿔주는 어노테이션
        userService.createUser(request);
    }
}
