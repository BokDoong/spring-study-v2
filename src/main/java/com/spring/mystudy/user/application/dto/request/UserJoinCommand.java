package com.spring.mystudy.user.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class UserJoinCommand {

    private String name;
    private String nickname;
    private String email;
    private String password;
    private String phoneNumber;
    private Integer genderId;
    private List<Long> categoryIds;
    private String firstAddress;
    private String secondAddress;
    private LocalDateTime birthDate;
}
