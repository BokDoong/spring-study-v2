package com.spring.mystudy.mission.application.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MissionCreateCommand {
    private int price;
    private String content;
    private LocalDateTime deadline;
}
