package com.spring.mystudy.user.application.dto.request;

public record TokenReissueCommand(String accessToken, String refreshToken) {
}
