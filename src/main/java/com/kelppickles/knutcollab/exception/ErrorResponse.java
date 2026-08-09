package com.kelppickles.knutcollab.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final boolean success;
    private final String message;

    public ErrorResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
