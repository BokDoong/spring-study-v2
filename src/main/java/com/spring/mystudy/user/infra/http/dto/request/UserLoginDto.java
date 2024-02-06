package com.spring.mystudy.user.infra.http.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserLoginDto {

    @NotNull(message = "email is null")
    private String email;
    @NotNull(message = "password is null")
    private String password;
}
