package com.kelppickles.knutcollab.exception;

import com.kelppickles.knutcollab.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ApiResponse<>(false, e.getMessage());
    }
}
