package com.spring.mystudy.user.infra.http.dto.response;

import lombok.Builder;

@Builder
public record TokenResponse(String accessToken, String refreshToken) {
}
