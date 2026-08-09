package com.kelppickles.knutcollab.exception;

import com.kelppickles.knutcollab.response.ApiResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ApiResponse<>(false, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ApiResponse<>(false, errors);
    }
}

// 2026-08-09T16:34:46.375+09:00  WARN 4056 --- [nio-8080-exec-2] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public com.kelppickles.knutcollab.response.ApiResponse<java.lang.Long> com.kelppickles.knutcollab.controller.ProjectController.createProject(com.kelppickles.knutcollab.dto.ProjectCreateRequest): [Field error in object 'projectCreateRequest' on field 'title': rejected value []; codes [NotBlank.projectCreateRequest.title,NotBlank.title,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [projectCreateRequest.title,title]; arguments []; default message [title]]; default message [공백일 수 없습니다]] ]