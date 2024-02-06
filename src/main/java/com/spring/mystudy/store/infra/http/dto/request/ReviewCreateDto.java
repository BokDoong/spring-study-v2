package com.spring.mystudy.store.infra.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewCreateDto {
    @NotBlank(message = "content is null")
    private String content;
    @NotNull(message = "rating is null")
    private float rating;
}
