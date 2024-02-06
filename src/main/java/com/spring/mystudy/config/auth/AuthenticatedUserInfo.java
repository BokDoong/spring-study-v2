package com.spring.mystudy.config.auth;

import com.spring.mystudy.user.domain.User;
import lombok.Getter;

@Getter
public class AuthenticatedUserInfo {
    private final long userId;

    public AuthenticatedUserInfo(User user) {
        userId = user.getId();
    }
}
