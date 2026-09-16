package com.kelppickles.knutcollab.controller;

import com.kelppickles.knutcollab.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/{projectId}/applications")
    public ResponseEntity<Void> apply(
            @PathVariable Long projectId,
            Authentication authentication
    ) {

        // 사용자의 정보를 가져옴
        // JWT Provider가 name에 User Email을 넣음.
        String userEmail = authentication.getName();

        applicationService.apply(userEmail, projectId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
