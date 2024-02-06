package com.spring.mystudy.store.appication.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
public class StoreCreateCommand {
    private String name;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String firstAddress;
    private String secondAddress;
    private List<Long> categoryIds;
    private String regionAddress;

    public Long getCategoryId() {
        return categoryIds.get(0);
    }
}
