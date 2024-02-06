package com.spring.mystudy.common.validator;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CategoriesExistValidator {

    KOR("한식", 1),
    JAP("일식", 2),
    EURO("양식", 3),
    CHIN("중식", 4),
    CHICK("치킨", 5),
    BOON("분식", 6),
    MEAT("고기/구이", 7),
    BOX("도시락", 8),
    SNACK("야식", 9),
    FAST("패스트푸드", 10),
    DES("디저트", 11),
    ASIAN("아시안푸드", 12)
    ;

    private final String label;
    private final long number;

    public long number() {
        return number;
    }

    CategoriesExistValidator(String label, long number) {
        this.label = label;
        this.number = number;
    }

    private static final Map<Long, CategoriesExistValidator> BY_NUMBER =
            Stream.of(values()).collect(Collectors.toMap(CategoriesExistValidator::number, Function.identity()));

    public static boolean validate(long id) {
        if (BY_NUMBER.get(id) == null) {
            return false;
        } else {
            return true;
        }
    }
}
