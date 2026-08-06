package com.kelppickles.knutcollab.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateRequest {
    private String email;
    private String nickname;
    private String password;
}
