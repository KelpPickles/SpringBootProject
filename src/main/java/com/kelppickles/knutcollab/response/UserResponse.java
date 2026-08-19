package com.kelppickles.knutcollab.response;

import com.kelppickles.knutcollab.entity.User;

public record UserResponse(
        Long id,
        String email,
        String nickname
) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }
}
