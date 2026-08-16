package com.kelppickles.knutcollab.dto;

import lombok.Getter;

@Getter
public class UserLoginRequest {

    private String email;
    private String password;
}
