package com.spring.mystudy.store.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BusinessHours {
    @Column(name = "open_time")
    private LocalTime openTime;
    @Column(name = "closed_time")
    private LocalTime closedTime;
}
