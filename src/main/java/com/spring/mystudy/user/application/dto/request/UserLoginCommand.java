package com.spring.mystudy.user.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserLoginCommand {
    private String email;
    private String password;
}
