package com.spring.mystudy.user.infra.http.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TokenReissueDto {
    @NotNull(message =  "AccessToken is null")
    private String accessToken;
    @NotNull(message =  "RefreshToken is null")
    private String refreshToken;
}
