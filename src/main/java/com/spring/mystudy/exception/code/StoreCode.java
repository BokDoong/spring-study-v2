package com.spring.mystudy.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreCode {

    NOT_FOUND("S-001"),
    NOT_OPENED("S-002")
    ;

    private final String code;
}
