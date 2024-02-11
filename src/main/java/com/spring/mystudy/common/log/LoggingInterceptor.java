package com.spring.mystudy.common.log;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {

        // Request Logging
        if (!verifyMultipartFileContained(request)) {
            RequestLogger.logging(request);
        } else {
            RequestLogger.loggingMultipartRequest(request);
        }

        // Successful Response Logging
        if (isSuccess(response.getStatus())) {
            ResponseLogger.logging(response);
        }
    }

    private boolean verifyMultipartFileContained(HttpServletRequest request) {
        return (boolean) request.getAttribute("isMultipartFile");
    }

    private boolean isSuccess(int responseStatus) {
        return !HttpStatus.valueOf(responseStatus).is4xxClientError() && !HttpStatus.valueOf(responseStatus)
                .is5xxServerError();
    }
}
