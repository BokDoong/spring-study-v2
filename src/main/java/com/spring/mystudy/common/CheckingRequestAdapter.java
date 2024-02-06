package com.spring.mystudy.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class CheckingRequestAdapter extends RequestBodyAdviceAdapter {

    @Override
    public Object afterBodyRead(final Object body, final HttpInputMessage inputMessage,
                                final MethodParameter parameter,
                                final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType) {

        // AdviceAdapter에서 Request 정보 불러오기 위해 추가
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // Logging
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("This is RequestBodyAdviceAdapter's Logs").append("\n");
        logBuilder.append("\n").append(getRequestURI(req)).append("\n");
        logBuilder.append(getAuthorization(req)).append("\n");
        logBuilder.append(getApiKey(req)).append("\n");
        // log.info(logBuilder.toString());

        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage,
                                           final MethodParameter parameter,
                                           final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object handleEmptyBody(final Object body, final HttpInputMessage inputMessage,
                                  final MethodParameter parameter, final Type targetType,
                                  final Class<? extends HttpMessageConverter<?>> converterType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(final MethodParameter methodParameter, final Type targetType,
                            final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    private String getRequestURI(HttpServletRequest request) {
        String httpMethod = "[HTTP Method] " + request.getMethod();
        String requestURI = "[Request URI] " + request.getRequestURI();

        return httpMethod + "\n" + requestURI;
    }

    private String getAuthorization(HttpServletRequest req) {
        String authorization = req.getHeader("authorization");

        if (authorization == null) {
            return "[Authorization] Empty";
        } else {
            return "[Authorization] " + authorization;
        }
    }

    private String getApiKey(HttpServletRequest req) {
        String apiKey = req.getHeader("apikey");

        if (apiKey == null) {
            return "[API Key] Empty";
        } else {
            return "[API Key] " + apiKey;
        }
    }
}
