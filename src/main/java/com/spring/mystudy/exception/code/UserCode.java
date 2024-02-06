package com.spring.mystudy.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserCode {

    USER_NOTFOUND("U-001"),
    CATEGORY_NOTFOUND("U-002"),
    ;

    private final String code;
}

