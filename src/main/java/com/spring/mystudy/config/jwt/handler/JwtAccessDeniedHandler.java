package com.spring.mystudy.config.jwt.handler;

import com.spring.mystudy.common.utils.logger.ResponseLogger;
import com.spring.mystudy.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.spring.mystudy.config.jwt.JwtException.*;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        ACCESS_DENIED.setResponse(response);
        ResponseLogger.loggingWithJWTExceptionInfo(ErrorResponse.toResponseEntity(ACCESS_DENIED), ACCESS_DENIED);
    }
}
