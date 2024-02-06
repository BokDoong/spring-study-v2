package com.spring.mystudy.user.infra.http.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.mystudy.common.validator.VerifyNotExistedCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserJoinDto {
    @NotBlank(message = "username is blank")
    private String name;
    @NotBlank(message = "nickname is blank")
    @Size(max = 8)
    private String nickname;
    @NotBlank(message = "email is blank")
    private String email;
    @NotBlank(message = "password is blank")
    private String password;
    @NotNull(message = "genderId is null")
    private Integer genderId;
    @VerifyNotExistedCategory
    private List<Long> categoryIds;
    private String phoneNumber;
    private String firstAddress;
    private String secondAddress;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime birthDate;
}
