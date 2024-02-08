package com.spring.mystudy.config.jwt;

import com.spring.mystudy.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@AllArgsConstructor
public enum JwtException {
    EXPIRED_TOKEN(UNAUTHORIZED, "만료된 토큰", "AUTH-001"),
    WRONG_TOKEN(UNAUTHORIZED, "유효하지 않은 토큰", "AUTH-002"),
    UNSUPPORTED_TOKEN(UNAUTHORIZED, "지원하지 않는 토큰 형식", "AUTH-003"),
    EMPTY_TOKEN(UNAUTHORIZED, "토큰이 없음", "AUTH-004"),
    ACCESS_DENIED(FORBIDDEN, "권한이 없음", "AUTH_005"),;

    private final HttpStatus status;
    private final String message;
    private final String code;

    public static void setResponse(HttpServletResponse response, JwtException exception) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.valueOf(createJwtExceptionBody(exception)));
    }

    private static ErrorResponse createJwtExceptionBody(JwtException exception) {
        return ErrorResponse.builder()
                .status(401)
                .error(exception.getStatus().name())
                .code(exception.getCode())
                .message(exception.getMessage())
                .build();
    }
}
