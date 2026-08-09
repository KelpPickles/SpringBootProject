package com.kelppickles.knutcollab.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final boolean success;
    private final T data;

    public ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

}
