package com.spring.mystudy.config.jwt.handler;

import com.spring.mystudy.common.log.ResponseLogger;
import com.spring.mystudy.config.jwt.JwtException;
import com.spring.mystudy.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        JwtException exception = getJwtException(request);
        exception.setResponse(response);
        ResponseLogger.loggingWithJWTExceptionInfo(ErrorResponse.toResponseEntity(exception), exception);
    }

    private JwtException getJwtException(HttpServletRequest request) {
        String exception = (String) request.getAttribute("exception");
        return exception == null ? JwtException.EMPTY_TOKEN : JwtException.valueOf(exception);
    }
}
