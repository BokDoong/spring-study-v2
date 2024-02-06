package com.spring.mystudy.store.appication.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewCreateCommand {
    String content;
    float rating;
}
